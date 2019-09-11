package com.github.kerraway.ioc.factory.xml;

import com.github.kerraway.ioc.factory.support.*;
import com.github.kerraway.ioc.io.ResourceLoader;
import com.github.kerraway.ioc.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从 XML 文件 读取 Bean 定义信息的实现类
 *
 * @author kerraway
 * @date 2019/09/11
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    protected XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 读取 XML 文档，解析文档对象，注册 Bean 定义信息
     *
     * @param location 资源路径
     * @throws Exception
     */
    public void readXml(String location) throws Exception {
        //创建一个资源加载器
        ResourceLoader resourceLoader = new ResourceLoader();
        //获取输入流
        try (InputStream inputStream = resourceLoader.getResource(location).getInputStream()) {
            //创建文档建造者工厂实例
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            //创建文档建造者
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            //获取文档对象
            Document doc = docBuilder.parse(inputStream);
            //解析文档对象，注册 Bean
            registerBeanDefinitions(doc);
        }
    }

    /**
     * 解析文档对象，注册 Bean 定义信息
     *
     * @param doc 文档对象
     */
    private void registerBeanDefinitions(Document doc) throws ClassNotFoundException {
        Assert.notNull(doc);
        //读取文档的根元素
        Element root = doc.getDocumentElement();
        //获取根元素的所有子元素
        NodeList nodes = root.getChildNodes();
        //遍历子元素
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            //类型判断
            if (node instanceof Element) {
                //强转为子类
                Element ele = (Element) node;
                //解析并注册 Bean 定义信息
                registerBeanDefinition(ele);
            }
        }
    }

    /**
     * 解析并注册 Bean 定义信息
     *
     * @param ele 某个元素
     * @throws ClassNotFoundException
     */
    private void registerBeanDefinition(Element ele) throws ClassNotFoundException {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(className);
        beanDefinition.setPropertyValues(parsePropertyValues(ele));
        //向 Bean 注册信息容器中添加 Bean 定义信息
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 解析某个元素的 property 属性集合
     *
     * @param ele 某个元素
     * @return 属性定义集合
     */
    private PropertyValues parsePropertyValues(Element ele) {
        //获取给定元素的 property 属性集合
        NodeList propertyNodes = ele.getElementsByTagName("property");
        //创建一个属性定义集合，用于收集后面解析得到的属性定义
        PropertyValues propertyValues = new PropertyValues(propertyNodes.getLength());
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyNode = propertyNodes.item(i);
            //类型判断
            if (propertyNode instanceof Element) {
                //强转为子类
                Element propertyEle = (Element) propertyNode;
                //属性名
                String name = propertyEle.getAttribute("name");
                //属性值
                String value = propertyEle.getAttribute("value");
                PropertyValue propertyValue;
                //判断属性值是否为空
                if (value != null && value.length() > 0) {
                    propertyValue = new PropertyValue(name, value);
                }
                //属性值为空，就取 ref
                else {
                    String ref = propertyEle.getAttribute("ref");
                    //如果 ref 同样为空，抛出异常
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException(String.format(
                                "Illegal xml configuration, <property> element for '%s' must have value or ref.", name));
                    }
                    //创建 Bean 引用，其中 Bean 对象暂时为 null
                    BeanReference beanRef = new BeanReference(ref);
                    propertyValue = new PropertyValue(name, beanRef);
                }
                //添加到属性定义集合
                propertyValues.addPropertyValue(propertyValue);
            }
        }
        //返回属性定义集合
        return propertyValues;
    }

}

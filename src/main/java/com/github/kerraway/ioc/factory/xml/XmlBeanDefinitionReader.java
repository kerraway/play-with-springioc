package com.github.kerraway.ioc.factory.xml;

import com.github.kerraway.ioc.factory.support.AbstractBeanDefinitionReader;
import com.github.kerraway.ioc.io.ResourceLoader;

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

}

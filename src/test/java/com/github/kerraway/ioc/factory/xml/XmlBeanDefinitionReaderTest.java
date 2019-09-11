package com.github.kerraway.ioc.factory.xml;

import com.github.kerraway.ioc.factory.AutowireBeanFactory;
import com.github.kerraway.ioc.factory.Bar;
import com.github.kerraway.ioc.factory.BeanFactory;
import com.github.kerraway.ioc.factory.Foo;
import com.github.kerraway.ioc.factory.support.BeanDefinition;
import com.github.kerraway.ioc.io.ResourceLoader;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author kerraway
 * @date 2019/09/11
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void functionTest() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.readXml("beans.xml");

        BeanFactory beanFactory = new AutowireBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            String name = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            beanFactory.registerBeanDefinition(name, beanDefinition);
        }

        Foo foo1 = (Foo) beanFactory.getBean("foo1");
        assertNotNull(foo1);
        assertEquals("foo1", foo1.getName());

        Bar bar1 = (Bar) beanFactory.getBean("bar1");
        assertNotNull(bar1);
        assertEquals("bar1", bar1.getName());
        assertNotNull(bar1.getFoo());
        assertEquals("foo2", bar1.getFoo().getName());
    }

}
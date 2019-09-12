package com.github.kerraway.ioc.factory.xml;

import com.github.kerraway.ioc.factory.*;
import com.github.kerraway.ioc.factory.support.BeanDefinition;
import com.github.kerraway.ioc.io.ResourceLoader;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 测试 {@link XmlBeanDefinitionReader} 和 {@link AutowireBeanFactory}
 *
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

        Foobar foobar1 = (Foobar) beanFactory.getBean("foobar1");
        assertNotNull(foobar1);
        assertEquals("foobar1", foobar1.getName());
        assertNotNull(foobar1.getBar());
        assertEquals("bar2", foobar1.getBar().getName());
        assertNotNull(foobar1.getBar().getFoo());
        assertEquals("foo3", foobar1.getBar().getFoo().getName());
        assertNotNull(foobar1.getFoo());
        assertEquals("foo4", foobar1.getFoo().getName());
    }

}
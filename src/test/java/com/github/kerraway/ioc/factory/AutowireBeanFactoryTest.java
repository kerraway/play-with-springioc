package com.github.kerraway.ioc.factory;

import com.github.kerraway.ioc.factory.support.BeanDefinition;
import com.github.kerraway.ioc.factory.support.PropertyValue;
import com.github.kerraway.ioc.factory.support.PropertyValues;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 测试 {@link AutowireBeanFactory}
 *
 * @author kerraway
 * @date 2019/09/12
 */
public class AutowireBeanFactoryTest {

    @Test
    public void functionTest() throws Exception {
        //创建 bean 的定义信息
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClass(Foo.class.getName());
        beanDefinition.setPropertyValues(new PropertyValues(new PropertyValue("name", "foo")));

        //创建 bean 工厂
        BeanFactory beanFactory = new AutowireBeanFactory();
        //将 bean 的定义信息添加到 bean 工厂
        beanFactory.registerBeanDefinition("foo", beanDefinition);

        //从 bean 工厂获取 bean 实例
        Foo foo = (Foo) beanFactory.getBean("foo");
        assertNotNull(foo);
        assertEquals("foo", foo.getName());
    }

}
package com.github.kerraway.ioc.factory;

import com.github.kerraway.ioc.factory.support.BeanDefinition;
import com.github.kerraway.ioc.factory.support.BeanReference;
import com.github.kerraway.ioc.factory.support.PropertyValue;
import com.github.kerraway.ioc.factory.support.PropertyValues;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

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
        BeanDefinition fooBeanDef = new BeanDefinition();
        fooBeanDef.setBeanClass(Foo.class.getName());
        fooBeanDef.setPropertyValues(new PropertyValues(new PropertyValue("name", "foo")));

        //创建 bean 工厂
        BeanFactory beanFactory = new AutowireBeanFactory();
        //将 bean 的定义信息添加到 bean 工厂
        beanFactory.registerBeanDefinition("foo", fooBeanDef);

        //从 bean 工厂获取 bean 实例
        Foo foo = (Foo) beanFactory.getBean("foo");
        assertNotNull(foo);
        assertEquals("foo", foo.getName());
    }

    @Test
    public void concurrentTest() throws Exception {
        //创建 bean 的定义信息
        //foo
        BeanDefinition fooBeanDef = new BeanDefinition();
        fooBeanDef.setBeanClass(Foo.class.getName());
        fooBeanDef.setPropertyValues(new PropertyValues(new PropertyValue("name", "foo")));
        //bar
        BeanDefinition barBeanDef = new BeanDefinition();
        barBeanDef.setBeanClass(Bar.class.getName());
        barBeanDef.setPropertyValues(new PropertyValues(
                new PropertyValue("name", "bar"),
                new PropertyValue("foo", new BeanReference("foo"))));

        //创建 bean 工厂
        BeanFactory beanFactory = new AutowireBeanFactory();
        //将 bean 的定义信息添加到 bean 工厂
        beanFactory.registerBeanDefinition("foo", fooBeanDef);
        beanFactory.registerBeanDefinition("bar", barBeanDef);

        //保证并发情况下不会重复创建 bean 实例
        assertBeans(beanFactory, "foo", Foo.class);
        assertBeans(beanFactory, "bar", Bar.class);
    }

    /**
     * 并发获取 bean 实例，需要保证几个线程获取到的 bean 实例为同一个，
     * 也就是说不会重复创建 bean 实例
     *
     * @param beanFactory bean 工厂
     * @param beanName    bean 名称
     * @param beanClass   bean 类型
     * @param <T>         bean 类型范型
     * @throws Exception
     */
    private <T> void assertBeans(BeanFactory beanFactory, String beanName, Class<T> beanClass) throws Exception {
        int n = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        List<Future<T>> beanFutures = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Future<T> beanFuture = executorService.submit(() -> {
                //从 bean 工厂获取 bean 实例
                T bean = (T) beanFactory.getBean(beanName);
                assertNotNull(bean);
                assertTrue(beanClass.isInstance(bean));
                return bean;
            });
            beanFutures.add(beanFuture);
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                T expected = beanFutures.get(i).get();
                T actual = beanFutures.get(j).get();
                assertEquals(expected, actual);
            }
        }
    }

}
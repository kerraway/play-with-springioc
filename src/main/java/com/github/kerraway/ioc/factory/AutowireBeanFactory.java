package com.github.kerraway.ioc.factory;

import com.github.kerraway.ioc.factory.support.BeanDefinition;
import com.github.kerraway.ioc.factory.support.BeanReference;
import com.github.kerraway.ioc.factory.support.PropertyValue;
import com.github.kerraway.ioc.util.Assert;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Bean 工厂实现类
 *
 * @author kerraway
 * @date 2019/09/11
 */
public class AutowireBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws BeansException {
        try {
            Object bean = beanDefinition.getBeanClass().newInstance();
            setProperties(bean, beanDefinition.getPropertyValues().getPropertyValues());
            return bean;
        } catch (Exception e) {
            throw new BeanCreationException(String.format("Create bean from %s failed.", beanDefinition));
        }
    }

    /**
     * 为 bean 设置属性
     *
     * @param bean           bean 实例
     * @param propertyValues 属性定义集合
     * @throws Exception
     */
    private void setProperties(Object bean, List<PropertyValue> propertyValues) throws Exception {
        Assert.notNull(bean);

        if (propertyValues == null) {
            return;
        }
        //遍历属性定义集合
        for (PropertyValue propertyValue : propertyValues) {
            Field filed = bean.getClass().getDeclaredField(propertyValue.getName());
            filed.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                //获取引用的 bean
                value = getReferencedBean((BeanReference) value);
            }
            //设置属性
            filed.set(bean, value);
        }
    }

    /**
     * 获取引用的 bean
     *
     * @param beanRef bean 引用
     * @return 引用的 bean
     * @throws Exception
     */
    private Object getReferencedBean(BeanReference beanRef) throws Exception {
        Assert.notNull(beanRef);

        if (beanRef.getBean() != null) {
            return beanRef.getBean();
        }
        //调用父类 AbstractBeanFactory#getBean(String) 方法
        //TODO 引用套引用，如何处理？
        Object bean = getBean(beanRef.getName());
        beanRef.setBean(bean);
        return bean;
    }

}

package com.github.kerraway.ioc.factory.support;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bean 定义信息
 *
 * @author kerraway
 * @date 2019/09/11
 */
@Data
@NoArgsConstructor
public class BeanDefinition {

    /**
     * Bean 实例
     */
    private Object bean;
    /**
     * Bean 类型
     */
    private Class beanClass;
    /**
     * Bean 属性定义集合
     */
    private PropertyValues propertyValues;

    /**
     * 使用全类名设置 Bean 类型
     *
     * @param className 全类名
     * @throws ClassNotFoundException
     */
    public void setBeanClass(String className) throws ClassNotFoundException {
        this.beanClass = Class.forName(className);
    }

}

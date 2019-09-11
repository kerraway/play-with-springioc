package com.github.kerraway.ioc.factory;

import com.github.kerraway.ioc.factory.support.BeanDefinition;

/**
 * Bean 工厂
 *
 * @author kerraway
 * @date 2019/09/11
 */
public interface BeanFactory {

    /**
     * 根据 Bean 名称从容器中获取 Bean 对象
     *
     * @param name Bean 名称
     * @return Bean 实例
     * @throws Exception
     */
    Object getBean(String name) throws Exception;

    /**
     * 将 Bean 注册到容器中
     *
     * @param name           Bean 名称
     * @param beanDefinition Bean 定义信息
     * @throws Exception
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;

}

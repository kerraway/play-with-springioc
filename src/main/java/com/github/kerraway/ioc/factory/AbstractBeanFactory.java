package com.github.kerraway.ioc.factory;

import com.github.kerraway.ioc.factory.support.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean 工厂抽象类
 *
 * @author kerraway
 * @date 2019/09/11
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * Bean 注册信息
     */
    private final Map<String, BeanDefinition> registry = new HashMap<>(1024);

    @Override
    public Object getBean(String name) throws BeansException {
        BeanDefinition beanDefinition = registry.get(name);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException(String.format("Can't find bean by name '%s'.", name));
        }
        if (beanDefinition.getBean() == null) {
            synchronized (beanDefinition) {
                if (beanDefinition.getBean() == null) {
                    Object bean = doCreateBean(beanDefinition);
                    beanDefinition.setBean(bean);
                }
            }
        }
        return beanDefinition.getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws BeansException {
        registry.put(name, beanDefinition);
    }

    /**
     * 由 bean 的定义信息，创建 bean
     *
     * @param beanDefinition bean 定义信息
     * @return bean 实例
     * @throws BeansException
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws BeansException;
}

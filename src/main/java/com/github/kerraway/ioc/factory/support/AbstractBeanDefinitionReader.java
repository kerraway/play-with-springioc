package com.github.kerraway.ioc.factory.support;

import com.github.kerraway.ioc.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Bean 定义信息读取抽象类
 *
 * @author kerraway
 * @date 2019/09/11
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    /**
     * Bean 注册信息
     */
    private final Map<String, BeanDefinition> registry;
    /**
     * 资源加载器
     */
    private final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>(1024);
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}

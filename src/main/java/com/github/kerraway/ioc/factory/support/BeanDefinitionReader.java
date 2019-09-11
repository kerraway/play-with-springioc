package com.github.kerraway.ioc.factory.support;

import com.github.kerraway.ioc.io.ResourceLoader;

import java.util.Map;

/**
 * Bean 定义信息读取接口
 *
 * @author kerraway
 * @date 2019/09/11
 */
public interface BeanDefinitionReader {

    /**
     * 获取 Bean 注册信息
     *
     * @return Bean 注册信息
     */
    Map<String, BeanDefinition> getRegistry();

    /**
     * 获取资源加载器
     *
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader();

}

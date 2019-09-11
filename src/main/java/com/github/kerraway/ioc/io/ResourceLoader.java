package com.github.kerraway.ioc.io;

import java.net.URL;

/**
 * 资源加载器
 *
 * @author kerraway
 * @date 2019/09/11
 */
public class ResourceLoader {

    /**
     * 根据资源路径，创建 "资源 URL" 对象，以便后面获取输入流
     *
     * @param location 资源路径
     * @return 资源 URL 对象
     */
    public ResourceUrl getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new ResourceUrl(url);
    }

}

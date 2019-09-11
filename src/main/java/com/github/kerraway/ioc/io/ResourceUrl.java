package com.github.kerraway.ioc.io;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 资源 URL
 *
 * @author kerraway
 * @date 2019/09/11
 */
@RequiredArgsConstructor
public class ResourceUrl implements Resource {

    /**
     * url
     */
    private final URL url;

    /**
     * 根据 url 获取输入流
     *
     * @return 输入流
     * @throws Exception
     */
    @Override
    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

}

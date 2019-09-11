package com.github.kerraway.ioc.io;

import java.io.InputStream;

/**
 * 资源接口
 *
 * @author kerraway
 * @date 2019/09/11
 */
public interface Resource {

    /**
     * 获取输入流
     *
     * @return 输入流
     * @throws Exception
     */
    InputStream getInputStream() throws Exception;

}

package com.github.kerraway.ioc.factory.support;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Bean 引用
 *
 * @author kerraway
 * @date 2019/09/11
 */
@Data
@RequiredArgsConstructor
public class BeanReference {

    /**
     * Bean 名称
     */
    private final String name;
    /**
     * Bean 对象
     */
    private Object bean;

}

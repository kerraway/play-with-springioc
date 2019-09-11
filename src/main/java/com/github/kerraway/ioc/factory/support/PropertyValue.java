package com.github.kerraway.ioc.factory.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 属性定义
 *
 * @author kerraway
 * @date 2019/09/11
 */
@Getter
@ToString
@RequiredArgsConstructor
public class PropertyValue {

    /**
     * 属性名称
     */
    private final String name;
    /**
     * 属性值
     */
    private final Object value;

}

package com.github.kerraway.ioc.factory.support;

import com.github.kerraway.ioc.util.Assert;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 属性定义集合
 *
 * @author kerraway
 * @date 2019/09/11
 */
@Getter
@ToString
public class PropertyValues {

    private final List<PropertyValue> propertyValues;

    public PropertyValues() {
        this(1024);
    }

    public PropertyValues(int initialCapacity) {
        Assert.isTrue(initialCapacity > 0, "Illegal Capacity: " + initialCapacity);
        this.propertyValues = new ArrayList<>(initialCapacity);
    }

    public PropertyValues(PropertyValue propertyValue) {
        this(1);
        this.propertyValues.add(propertyValue);
    }

    public PropertyValues(PropertyValue first, PropertyValue... others) {
        this(1 + others.length);
        this.propertyValues.add(first);
        this.propertyValues.addAll(Arrays.asList(others));
    }

    /**
     * 添加属性定义
     *
     * @param propertyValue 属性定义
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        Assert.notNull(propertyValue);
        propertyValues.add(propertyValue);
    }
}

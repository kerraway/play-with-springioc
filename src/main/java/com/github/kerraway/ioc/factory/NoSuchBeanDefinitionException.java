package com.github.kerraway.ioc.factory;

/**
 * 当 {@link BeanFactory} 获取 bean 时，如果找不到对应的 bean 定义信息，就抛出该异常
 *
 * @author kerraway
 * @date 2019/09/11
 */
public class NoSuchBeanDefinitionException extends BeansException {

    private static final long serialVersionUID = -4591646448389629404L;

    public NoSuchBeanDefinitionException() {
        super();
    }

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBeanDefinitionException(Throwable cause) {
        super(cause);
    }

}

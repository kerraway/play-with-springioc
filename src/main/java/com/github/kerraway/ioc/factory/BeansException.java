package com.github.kerraway.ioc.factory;

/**
 * Bean 相关异常
 *
 * @author kerraway
 * @date 2019/09/12
 */
public abstract class BeansException extends RuntimeException {

    private static final long serialVersionUID = -1239127843012565111L;

    public BeansException() {
        super();
    }

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeansException(Throwable cause) {
        super(cause);
    }

}

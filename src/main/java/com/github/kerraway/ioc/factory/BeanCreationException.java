package com.github.kerraway.ioc.factory;

/**
 * 当 {@link BeanFactory} 创建 bean 失败，就抛出该异常
 *
 * @author kerraway
 * @date 2019/09/12
 */
public class BeanCreationException extends BeansException {

    private static final long serialVersionUID = 2372873535586254384L;

    public BeanCreationException() {
        super();
    }

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(Throwable cause) {
        super(cause);
    }
}

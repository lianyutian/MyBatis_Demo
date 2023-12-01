package com.github.lianyutian.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liming
 * @version 1.0
 * @since 2023/12/1 9:27
 */
public class LoggingHandler implements InvocationHandler {
    private Object target;

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking method: " + method.getName());

        // 调用实际对象的方法
        Object result = method.invoke(target, args);

        System.out.println("After invoking method: " + method.getName());

        return result;
    }
}

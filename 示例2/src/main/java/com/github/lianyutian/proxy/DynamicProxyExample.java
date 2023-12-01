package com.github.lianyutian.proxy;

import java.lang.reflect.Proxy;

/**
 * @author liming
 * @version 1.0
 * @since 2023/12/1 9:28
 */
public class DynamicProxyExample {
    public static void main(String[] args) {
        // 创建实际对象
        Calculator calculator = new CalculatorImpl();

        // 创建动态代理对象
        Calculator proxyCalculator = (Calculator) Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[]{Calculator.class},
                new LoggingHandler(calculator)
        );

        // 使用代理对象调用方法
        int result = proxyCalculator.add(3, 5);
        System.out.println("Result: " + result);
    }
}

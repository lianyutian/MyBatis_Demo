package com.github.lianyutian.proxy;

/**
 * @author liming
 * @version 1.0
 * @since 2023/12/1 9:25
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        System.out.println("invoking method: add");
        return a + b;
    }
}

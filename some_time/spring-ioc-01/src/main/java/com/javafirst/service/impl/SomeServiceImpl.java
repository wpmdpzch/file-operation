package com.javafirst.service.impl;

import com.javafirst.service.SomeService;

public class SomeServiceImpl implements SomeService {

    public void doSome() {
        System.out.println("原来的业务方法，在实现类中");
    }

    public void doSome(String name, Integer num) {
        System.out.println("原来的业务方法，在实现类中，有两个参数：" + name + "->" + num);
    }

    public String returnPrice(double price, float discount) {
        if (discount > 0.0f && price > 0.0) {
            return "折扣价：" + (price * discount);
        }
        return "原价：" + price;
    }
}

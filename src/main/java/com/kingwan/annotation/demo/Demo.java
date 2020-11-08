package com.kingwan.annotation.demo;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：jdk自带注解:
 */

public class Demo{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Father father = new Father();
        father.eat();
        father.sleep();
    }
}

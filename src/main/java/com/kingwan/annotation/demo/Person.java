package com.kingwan.annotation.demo;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：
 */
public class Person {

    void eat(){};//吃饭

    /**
     * 加了@Deprecated注解
     * 表示该方法不再推荐使用，未来jdk可能会弃用该方法
     */
    @Deprecated
    void sleep(){};//睡觉
}

package com.kingwan.annotation.demo;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：父亲类实现Person接口，重写方法
 */
public class Father extends Person{

    @Override
    public void eat() {
        System.out.println("父亲在吃饭");
    }


}

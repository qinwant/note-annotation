package com.kingwan.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：自定义注解，模拟@table注解
 */
@Target(ElementType.TYPE)//作用在类/接口上
@Retention(RetentionPolicy.RUNTIME)//保留作用域：保留到运行时
public @interface KingwanTable {
    String value();//参数
}

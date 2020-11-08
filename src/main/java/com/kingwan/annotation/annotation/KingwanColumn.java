package com.kingwan.annotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：自定义注解
 */
@Target(ElementType.FIELD)//表示作用在字段上
@Retention(RetentionPolicy.RUNTIME)
public @interface KingwanColumn {
    String value();
}

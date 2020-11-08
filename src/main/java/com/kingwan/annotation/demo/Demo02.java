package com.kingwan.annotation.demo;

import com.kingwan.annotation.annotation.KingwanColumn;
import com.kingwan.annotation.annotation.KingwanTable;
import com.kingwan.annotation.entity.Student;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kingwan on 2020/11/7.
 * 说明：自定义注解模拟查询语句的SQL生成
 */
public class Demo02 {
    public static void main(String[] args) throws Exception {
        StringBuffer sql = new StringBuffer("");
        Student student = new Student();
        //初始化信息
        init(student);

//        Class<?> aClass = Class.forName("com.kingwan.annotation.entity.Student");
        Class<? extends Student> aClass = student.getClass();
        //1. 判断注解是否存在类上
        boolean exist = aClass.isAnnotationPresent(KingwanTable.class);
        String tableName = null;
        if(exist){
            //1.1 存在注解即获取注解值---(表名)
            KingwanTable annotation = aClass.getAnnotation(KingwanTable.class);
            tableName = annotation.value();
            sql.append("select * from ").append(tableName).append(" where 1=1");
        }
        System.out.println(sql);
        //2. 获取属性上的注解
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            //2.1 遍历属性上是否有KingwanColumn注解
            KingwanColumn column = field.getAnnotation(KingwanColumn.class);
            if( column != null){
                //2.1.1 获取该属性的值
                String fieldName = field.getName();//属性名
                String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);//方法名
                Method method = aClass.getMethod(methodName);
                Object invoke = method.invoke(student);
                if(invoke instanceof String){
                    String value = (String) invoke;
                    sql.append(" and ").append(column.value()).append("=").append("'").append(value).append("'");
                }else if (invoke instanceof Date){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
                    String date = dateFormat.format((Date) invoke);
                    sql.append(" and ").append(column.value()).append("=").append("'").append(date).append("'");
                }else {
                    sql.append(" and ").append(column.value()).append("=").append(invoke);
                }
            }else {
                System.out.println(field.getName()+"字段没有KingwanColumn注解");
            }
        }
        System.out.println(sql);

    }


    private static void init(Student student) {
           student.setStuName("kingwan");
           student.setStuAge(18);
           student.setStuBirth(new Date());
    }

}

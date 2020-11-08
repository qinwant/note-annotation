### 注解

[TOC]

#### 什么是注解

想要了解某个知识点，我首先推荐的都是去官网查看，下面看看Java官方对注解的解释：

> *Annotations*, a form of metadata, provide data about a program that is not part of the program itself. Annotations have no direct effect on the operation of the code they annotate.
>
> 注解是元数据的一种形式，它提供有关程序的数据，但这些数据不是程序本身的一部分。注解对它们注释的代码的操作没有直接影响。

#### 从`@Override`开始

```java
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

短短的5行，好像除了第一行，其他啥都不知道。。。不急，我们一行一行来解读！

- 注解导入了一个`annotation`包
- 注解的“套娃”行为`@Target(ElementType.METHOD)`、`@Retention(RetentionPolicy.SOURCE)`
- 不同于接口和类的声明`public @interface Override {
  }`

除了对新注解不认识，我们大致可以了解到注解的定义格式，`修饰符 @interface 注解名{}`。（有点接口的感觉）

#### 禁止套娃——元注解

##### **@Retention**

表示如何存储被标记的注解(指定存储级别)，有以下三个级别

- RetentionPolicy.SOURCE：只保留到源码级别，在编译阶段会被忽略，所以他们不会被写入字节码。
- RetentionPolicy.CLASS：（默认）编译级别，在编译时由编译器保留，但被Java虚拟机(JVM)忽略。
- RetentionPolicy.RUNTIME：由JVM保留，可以在运行时环境使用。

##### **@Target**

表示被标记的注解可以用于哪种java元素(类、接口、属性、方法......)，有以下八种

| 作用域                      | 解释                 |
| --------------------------- | -------------------- |
| ElementType.ANNOTATION_TYPE | 可用于注解类型       |
| ElementType.CONSTRUCTOR     | 可以用于构造函数     |
| ElementType.FIELD           | 可以用于字段或者属性 |
| ElementType.LOCAL_VARIABLE  | 可以用于局部变量     |
| ElementType.METHOD          | 可以用于方法级注解   |
| ElementType.PACKAGE         | 可以用于包声明       |
| ElementType.PARAMETER       | 可以用于方法的参数   |
| ElementType.TYPE            | 可以用于类的任何元素 |



##### **@Documented**

无论何时使用指定的注解，都应使用Javadoc工具记录这些元素。（即会在生成的javadoc中加入注解说明）

##### **@Inherited**

可以从超类继承注释类型，仅用于类的声明(接口不会继承)

##### **@Repeatable**

在Java SE 8中引入的，表示标记的注释可以多次应用于相同的声明或类型使用。

#### 注解的分类

通过对元注解的了解，我明白了一个注解都是由这些元注解修饰而来，而且我们也收获了一个重要信息——**注解可以修饰注解**

这样无限的套娃，就会有各种各样的注解，那么到底有哪些注解呢？常见的注解大致分为以下四类：

##### 元注解

即上文提及的5个元注解

##### jdk注解

常见的如[`@Override`](https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html) [`@Deprecated`](https://docs.oracle.com/javase/8/docs/api/java/lang/Deprecated.html) [`@SuppressWarnings`](https://docs.oracle.com/javase/8/docs/api/java/lang/SuppressWarnings.html) [`@SafeVarargs`](https://docs.oracle.com/javase/8/docs/api/java/lang/SafeVarargs.html) [`@FunctionalInterface`](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html)

##### 第三方注解

即第三方框架提供的注解，例如自动注入依赖`@Autowired`、`@Controller`等

##### 自定义注解

即开发人员根据项目需求自定义的注解，用于一些工具在编译、运行时进行解析和使用，起到说明、配置的功能。

##### 注解有什么用

- 在编译时进行格式检查。如`@Override `
- 跟踪代码依赖性，实现替代配置文件功能。通过处理注解信息生成代码、XML文件。
- 一些注释可以在运行时进行检查

##### 结尾一张图

一张思维导图总结一下内容，保存下来，时常复习！

![](https://cdn.jsdelivr.net/gh/qinwant/Figurebed/img/20201108%E6%B3%A8%E8%A7%A3%E6%80%9D%E7%BB%B4%E5%AF%BC%E5%9B%BE.png)















































>Annotations in Java is all about adding meta-data facility to the Java Elements. Like Classes, Interfaces or Enums, Annotations define a type in Java and they can be applied to several Java Elements.

java5之后提供的一种用来将任何的信息或元数据（metadata）与程序元素（类、方法、成员变量）进行关联的机制

#### 注解有什么用

起到说明、配置的作用

生成文档（javadoc）

跟踪代码依赖性，实现替代配置文件

在编译时进行格式检查（@override）

#### 常见注解

##### 注解的通用格式

```java
@Target(ElementType.TYPE)//注解作用的目标位置
@Retention(RetentionPolicy.RUNTIME)//注解保留的时间域
@Documented//生成说明文档
public @interface AnnotationName {
    String value();
}
```

##### JDK自带注解

- `@Override`:作用在方法上，子类覆盖父类，保留到编译时期
- `@Deprecated`:均可，表示不推荐使用发出警告，保留到运行时期
- `@SuppressWarnings`:均可，忽略警告，例如：`@SuppressWarnings("deprecation")`

##### 第三方注解

常见框架的注解

`@Controller`、`@Bean`、`@Autowired`等等，对注解有个基础的认识有助于我们了解别人写的注解适合含义，对代码的理解更加深刻。

##### 自定义注解

*目标场景*

> 使用自定义注解，通过在实体类及其属性上加注解，实现对实体类的查询sql语句的构造

*1.自定义注解`@KingwanTable`、`KingwanColumn`*

- `@KingwanTable`:注解实体类对应的表名

  ```java
  @Target(ElementType.TYPE)//作用在类/接口上
  @Retention(RetentionPolicy.RUNTIME)//保留作用域：保留到运行时
  public @interface KingwanTable {
      String value();//参数:表名
  }
  ```

- `@KingwanColumn`:注解实体类属性对应的表字段名

  ```java
  @Target(ElementType.FIELD)//表示作用在字段上
  @Retention(RetentionPolicy.RUNTIME)//保留到运行时
  public @interface KingwanColumn {
      String value();//参数：字段名
  }
  ```

*2.创建实体类，给实体类添加上自定义注解*

```java
@Data//简化实体类的set、get方法
@KingwanTable("t_student")
public class Student {
    @KingwanColumn("stu_name")
    private String stuName;
    @KingwanColumn("stu_age")
    private Integer stuAge;
    @KingwanColumn("stu_birth")
    private Date stuBirth;
}
```

*3.初始化`student`类，填充信息*

```java
public static void main(String[] args) {
    Student student = new Student();
    //初始化信息
    init(student);
}
private static void init(Student student) {
    student.setStuName("kingwan");
    student.setStuAge(18);
    student.setStuBirth(new Date());
}
```

*4.通过反射机制获取注解*

- 获取类上的注解`@KingwanTable`

```java
public static void main(String[] args) throws Exception {
    StringBuffer sql = new StringBuffer("");//即将拼接的SQL语句
    Student student = new Student();
    //初始化信息
    init(student);
    //反射获取class类
    Class<? extends Student> aClass = student.getClass();
    //1. 判断实体类上是否存在注解@KingwanTable
    boolean exist = aClass.isAnnotationPresent(KingwanTable.class);//传入我们自定义的注解类
    String tableName = null;
    if(exist){
        //1.1 存在注解即获取注解值---(表名)
        KingwanTable annotation = aClass.getAnnotation(KingwanTable.class);
        tableName = annotation.value();
        sql.append("select * from ").append(tableName).append(" where 1=1");//拼接SQL
    }
    System.out.println(sql);
}
```

此时SQL的结果：![](https://cdn.jsdelivr.net/gh/qinwant/Figurebed/img/20201107%E6%B3%A8%E8%A7%A301.png)

- 获取属性上的注解`@KingwanColumn`

```java
//2. 获取属性上的注解
Field[] fields = aClass.getDeclaredFields();
for (Field field : fields) {
    //2.1 遍历每个属性上是否有KingwanColumn注解
    KingwanColumn column = field.getAnnotation(KingwanColumn.class);
    if( column != null){
        //2.1.1 获取该属性的值
        String fieldName = field.getName();//属性名
        String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);//构造getter方法
        Method method = aClass.getMethod(methodName);
        //通过反射代理调用get方法，获取属性的值（name='kingwan',age=18....）
        Object invoke = method.invoke(student);
        if(invoke instanceof String){
            String value = (String) invoke;
            //sql拼接
            sql.append(" and ").append(column.value()).append("=").append("'")
                .append(value).append("'"); 
        }
    }
    System.out.println(sql);
}
```

此时SQL的结果：![image-20201107204502067](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20201107204502067.png)

这样，是不是就达到了我们的效果了，对于任意实体类，我们都可以通过加上该注解实现一个简单的查找SQL的生成

#### 总结
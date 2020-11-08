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



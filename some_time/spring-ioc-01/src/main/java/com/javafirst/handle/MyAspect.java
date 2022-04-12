package com.javafirst.handle;

import org.aspectj.lang.annotation.*;

@Aspect
public class MyAspect {
    /**
     * execution() 这个叫 切点表达式
     * <p>
     * 语法依次是：方法修饰符(可省略)、方法返回类型、方法所在包名全路径+方法名+方法参数类型列表
     */
    @Before(value="execution(public void com.javafirst.service.impl.SomeServiceImpl.*(..))")
    public void aop_before(){
        System.out.println("在原有业务方法之前执行逻辑，这里自动代理功能要执行的代码.");
    }

    @AfterReturning(value = "execution(java.lang.String com.javafirst.service.impl.*.return*(..))",returning = "res")
    public void aop_afterReturning(Object res){
        System.out.println("目标方法执行结果：" + res);
        System.out.println("在目标方法执行后 输出.");
    }

    @Before(value = "aop_pointcut()")
    public void aop_before_pointcut(){
        System.out.println("aop_before_pointcut() 前置通知：切面类中的输出内容!\n");
    }

    @After(value="aop_pointcut()")
    public void aop_after_pointcut(){
        System.out.println("\naop_after() 后置通知：切面类中的输出内容!");
    }

    /**
     * 定义 @Pointcut 注解
     */
    @Pointcut(value = "execution(* *..SomeServiceImpl.return*(..))")
    private void aop_pointcut() {
    }
}

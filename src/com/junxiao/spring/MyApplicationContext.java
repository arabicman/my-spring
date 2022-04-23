package com.junxiao.spring;

import com.junxiao.spring.annotation.ComponentScan;

/***
 * Spring容器类
 */
public class MyApplicationContext {

    private Class configClass;

    public MyApplicationContext(Class configClass) {
        this.configClass = configClass;
        //扫描注解
        if (configClass.isAnnotationPresent(ComponentScan.class)) {

        }
    }

    public Object getBean(String beanName) {

        return null;
    }
}

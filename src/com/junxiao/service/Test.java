package com.junxiao.service;

import com.junxiao.spring.MyApplicationContext;

public class Test {
    public static void main(String[] args) {

        MyApplicationContext applicationContext = new MyApplicationContext(AppConfig.class);

        UserService userService = (UserService)applicationContext.getBean("userService");


    }
}

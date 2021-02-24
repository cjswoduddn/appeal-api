package com.appeal.api.portfolio.controller;

import org.junit.jupiter.api.Test;

class YoungWooTemplateOneControllerTest {
    static public class Parent{

    }
    static public class Child extends Parent{

    }
    static public class Child2 extends Parent{}

    @Test
    void test(){
        Parent child = new Child();
        System.out.println(child instanceof Child2);
    }

}
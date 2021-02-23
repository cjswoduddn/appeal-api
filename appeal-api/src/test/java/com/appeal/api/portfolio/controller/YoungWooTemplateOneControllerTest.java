package com.appeal.api.portfolio.controller;

import com.appeal.api.portfolio.domain.Portfolio;
import com.appeal.api.portfolio.domain.YoungWooTemplateOne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
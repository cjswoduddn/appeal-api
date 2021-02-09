package com.appeal.api.portfolio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PortfolioServiceTest {

    @Autowired PortfolioService service;

    @Test
    @DisplayName("get할 시 dtype에 맞게 다 fetch되는지")
    public void getTest() throws Exception{
        //given
        Portfolio po = service.getById(2L);

        //when
        System.out.println("po = " + po);

        //then
    }

}
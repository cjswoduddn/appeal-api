package com.appeal.api.portfolio.service;

import com.appeal.api.common.exception.NoPortfolioFoundException;
import com.appeal.api.portfolio.repository.PortfolioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class PortfolioServiceTest {

    @Test
    @DisplayName("포트폴리오 id가 없음")
    public void getByIdFail() throws Exception{
        //given
        PortfolioRepository repository = mock(PortfolioRepository.class);
        PortfolioService service = new PortfolioService(repository);

        //when
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        //then
        Assertions.assertThrows(NoPortfolioFoundException.class, ()->service.getById(anyLong()));
    }

}
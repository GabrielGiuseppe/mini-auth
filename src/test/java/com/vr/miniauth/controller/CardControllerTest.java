package com.vr.miniauth.controller;

import com.vr.miniauth.builder.RandomUtils;
import com.vr.miniauth.builder.TestCardRequestBuilder;
import com.vr.miniauth.builder.TestCardResponseBuilder;
import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @InjectMocks
    CardController controller;

    @Mock
    CardService service;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Nested
    class CreateTest{

        @Test
        void testShouldCreateCard(){
            //Arrange
            final var cardRequest = new TestCardRequestBuilder().build();
            final var cardResponse = new BaseReturn<>(new TestCardResponseBuilder().build());

            when(service.create(cardRequest)).thenReturn(cardResponse);

            //Act
            controller.create(cardRequest);

            //Assert
            var inOrder = inOrder(service);
            inOrder.verify(service).create(cardRequest);
            inOrder.verifyNoMoreInteractions();
        }
    }

    @Nested
    class GetBalanceTest{

        @Test
        void testShouldGetBalance(){
            //Arrange
            final var cardNumber = RandomUtils.randomCardNumber();
            final var balanceResponse = new BaseReturn<>(BigDecimal.ONE);

            when(service.getBalance(cardNumber)).thenReturn(balanceResponse);

            //Act
            controller.getBalance(cardNumber);

            //Assert
            var inOrder = inOrder(service);
            inOrder.verify(service).getBalance(cardNumber);
            inOrder.verifyNoMoreInteractions();
        }
    }



}
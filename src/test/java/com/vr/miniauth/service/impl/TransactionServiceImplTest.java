package com.vr.miniauth.service.impl;

import com.vr.miniauth.builder.TestCardEntityBuilder;
import com.vr.miniauth.builder.TestTransactionRequestBuilder;
import com.vr.miniauth.exception.BaseException;
import com.vr.miniauth.service.CardService;
import com.vr.miniauth.utils.CryptUtils;
import com.vr.miniauth.utils.ProjectConstants;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl service;

    @Mock
    CardService cardService;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }


    @Nested
    class ApproveTransactionTest{
        @Test
        void testShouldApproveTransaction(){
            //Arrange
            final var request = new TestTransactionRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .withValue(new BigDecimal(10))
                    .build();

            final var cardModel = new TestCardEntityBuilder()
                    .withId(01L)
                    .withNumber("1234123412341234")
                    .withPassword(CryptUtils.encryptPassword("1234"))
                    .withBalance(new BigDecimal(10))
                    .build();

            when(cardService.getCard(request.getCardNumber())).thenReturn(cardModel);

            //Act
            final var result = service.approveTransaction(request);
            //Assert
            assertEquals(ProjectConstants.OK_TRANSACTIUON, result.data);
            var inOrder = inOrder(cardService);
            inOrder.verify(cardService).getCard(request.getCardNumber());
            inOrder.verify(cardService).saveCard(cardModel);
            inOrder.verifyNoMoreInteractions();
        }
        @Test
        void testShouldThrowPasswordNotMatchException(){
            //Arrange
            final var request = new TestTransactionRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .withValue(new BigDecimal(10))
                    .build();

            final var cardModel = new TestCardEntityBuilder()
                    .withId(01L)
                    .withNumber("1234123412341234")
                    .withPassword(CryptUtils.encryptPassword("1235"))
                    .withBalance(new BigDecimal(10))
                    .build();

            final var exception = new BaseException(HttpStatus.UNPROCESSABLE_ENTITY, ProjectConstants.PASSWORD_NOT_MATCH);

            when(cardService.getCard(request.getCardNumber())).thenReturn(cardModel);

            //Assert
            assertThatThrownBy(() -> service.approveTransaction(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }
        @Test
        void testShouldThrowInsufficientBalanceException(){
            //Arrange
            final var request = new TestTransactionRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .withValue(new BigDecimal(10))
                    .build();

            final var cardModel = new TestCardEntityBuilder()
                    .withId(01L)
                    .withNumber("1234123412341234")
                    .withPassword(CryptUtils.encryptPassword("1234"))
                    .withBalance(new BigDecimal(9))
                    .build();

            final var exception = new BaseException(HttpStatus.UNPROCESSABLE_ENTITY, ProjectConstants.INSUFFICIENT_BALANCE);

            when(cardService.getCard(request.getCardNumber())).thenReturn(cardModel);

            //Assert
            assertThatThrownBy(() -> service.approveTransaction(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }

        @Test
        void testShouldThrowCardNotFoundException(){
            //Arrange
            final var request = new TestTransactionRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .withValue(new BigDecimal(10))
                    .build();

            final var exception = new BaseException(HttpStatus.NOT_FOUND, ProjectConstants.CARD_NOT_FOUND);

            when(cardService.getCard(request.getCardNumber())).thenThrow(exception);

            //Assert
            assertThatThrownBy(() -> service.approveTransaction(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }
    }
}
package com.vr.miniauth.service.impl;

import com.vr.miniauth.builder.TestCardEntityBuilder;
import com.vr.miniauth.builder.TestCardRequestBuilder;
import com.vr.miniauth.exception.BaseException;
import com.vr.miniauth.mapper.CardMapper;
import com.vr.miniauth.repository.CardRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
import java.util.Optional;

import static com.vr.miniauth.utils.CryptUtils.decryptPassword;
import static com.vr.miniauth.utils.CryptUtils.encryptPassword;
import static com.vr.miniauth.utils.MaskUtils.applyCardNumberMask;
import static com.vr.miniauth.utils.MaskUtils.removeCardNumberMask;
import static com.vr.miniauth.utils.ProjectConstants.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class CardServiceImplTest {

    @InjectMocks
    CardServiceImpl service;

    @Mock
    CardRepository repository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Nested
    class CreateTest {

        @Test
        void testShouldCreate() {
            //Arrange
            final var request = new TestCardRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .build();
            var cardModel = Optional.of(new TestCardEntityBuilder()
                    .withNumber(applyCardNumberMask("1234123412341234"))
                    .withPassword(encryptPassword("1234"))
                    .build());
            var model = CardMapper.mapToModel(request);

            when(repository.getByNumber(applyCardNumberMask(request.getCardNumber())))
                    .thenReturn(Optional.empty());
            when(repository.save(model)).thenReturn(cardModel.get());
            //Act
            final var result = service.create(request).data;
            //Assert
            assertEquals(removeCardNumberMask(model.getNumber()), result.getCardNumber());
            assertEquals(decryptPassword(model.getPassword()), result.getPassword());
            var inOrder = Mockito.inOrder(repository);
            inOrder.verify(repository).getByNumber(applyCardNumberMask(request.getCardNumber()));
            inOrder.verify(repository).save(model);
            inOrder.verifyNoMoreInteractions();
        }

        @Test
        void testShouldThrowCardAlreadyRegistered() {
            //Arrange
            final var request = new TestCardRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("1234")
                    .build();
            final var cardModel = Optional.of(new TestCardEntityBuilder().build());
            final var exception = new BaseException(HttpStatus.UNPROCESSABLE_ENTITY, CARD_NUMBER_ALREADY_REGISTERED);
            when(repository.getByNumber(applyCardNumberMask(request.getCardNumber())))
                    .thenReturn(cardModel);

            //Assert
            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }

        @Test
        void testShouldThrowCardNumberNotCorrectSize() {
            //Arrange
            final var request = new TestCardRequestBuilder()
                    .withCardNumber("12341234123412341")
                    .withPassword("1234")
                    .build();
            final var cardModel = Optional.of(new TestCardEntityBuilder().build());
            final var exception = new BaseException(HttpStatus.BAD_REQUEST, CARD_NUMBER_NOT_CORRECT_SIZE);
            //Assert
            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }

        @Test
        void testShouldThrowCardPasswordNotCorrectSize() {
            //Arrange
            final var request = new TestCardRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("12341")
                    .build();
            final var exception = new BaseException(HttpStatus.BAD_REQUEST, PASSWORD_NOT_CORRECT_SIZE);
            //Assert
            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }

        @Test
        void testShouldThrowCardPasswordNotCorrectType() {
            //Arrange
            final var request = new TestCardRequestBuilder()
                    .withCardNumber("1234123412341234")
                    .withPassword("123@")
                    .build();
            final var exception = new BaseException(HttpStatus.BAD_REQUEST, PASSWORD_NOT_CORRECT_TYPE);
            //Assert
            assertThatThrownBy(() -> service.create(request))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }
    }

    @Nested
    class GetBalanceTest {
        @Test
        void testShouldGetBalance() {
            //Arrange
            final var cardNumber = "1234123412341234";
            final var cardModel = new TestCardEntityBuilder()
                    .withNumber("1234123412341234")
                    .withPassword("1234")
                    .withBalance(new BigDecimal(30))
                    .build();
            when(repository.getByNumber(applyCardNumberMask(cardNumber)))
                    .thenReturn(Optional.of(cardModel));
            //Act
            final var result = service.getBalance(cardNumber).data;
            //Assert
            assertEquals(cardModel.getBalance(), result);
            var inOrder = Mockito.inOrder(repository);
            inOrder.verify(repository).getByNumber(applyCardNumberMask(cardNumber));
            inOrder.verifyNoMoreInteractions();
        }

        @Test
        void testShouldThrowCardNumberNotCorrectSize() {
            //Arrange
            final var cardNumber = "12341234123412340";
            final var exception = new BaseException(HttpStatus.BAD_REQUEST, CARD_NUMBER_NOT_CORRECT_SIZE);

            //Assert
            assertThatThrownBy(() -> service.getBalance(cardNumber))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }
    }

    @Nested
    class GetCardTest {
        @Test
        void testShouldGetCard() {
            //Arrange
            final var cardNumber = "1234123412341234";
            final var cardModel = new TestCardEntityBuilder()
                    .withId(30L)
                    .withNumber(cardNumber)
                    .withPassword(encryptPassword("1234"))
                    .withBalance(new BigDecimal(10))
                    .build();
            when(repository.getByNumber(applyCardNumberMask(cardNumber)))
                    .thenReturn(Optional.of(cardModel));

            //Act
            final var result = service.getCard(cardNumber);

            //Assert
            assertEquals(cardModel.getId(), result.getId());
            assertEquals(cardModel.getNumber(), result.getNumber());
            assertEquals(cardModel.getPassword(), result.getPassword());
            assertEquals(cardModel.getBalance(), result.getBalance());
            var inOrder = Mockito.inOrder(repository);
            inOrder.verify(repository).getByNumber(applyCardNumberMask(cardNumber));
            inOrder.verifyNoMoreInteractions();
        }

        @Test
        void testShouldThrowCardNotFoundException() {
            //Arrange
            final var cardNumber = "1234123412341234";
            when(repository.getByNumber(applyCardNumberMask(cardNumber)))
                    .thenReturn(Optional.empty());
            final var exception = new BaseException(HttpStatus.NOT_FOUND, CARD_NOT_FOUND);

            //Assert
            assertThatThrownBy(() -> service.getCard(cardNumber))
                    .isInstanceOf(BaseException.class)
                    .hasMessage(exception.getMessage());
        }
    }


    @Nested
    class SaveCardTest {

        @Test
        void testShouldGetCard() {
            //Arrange
            final var cardModel = new TestCardEntityBuilder()
                    .withId(30L)
                    .withNumber(applyCardNumberMask("1234123412341234"))
                    .withPassword(encryptPassword("1234"))
                    .withBalance(new BigDecimal(10))
                    .build();

            when(repository.save(cardModel)).thenReturn(cardModel);

            //Act
            final var result = service.saveCard(cardModel);

            //Assert
            assertEquals(cardModel.getId(), result.getId());
            assertEquals(cardModel.getNumber(), result.getNumber());
            assertEquals(cardModel.getPassword(), result.getPassword());
            assertEquals(cardModel.getBalance(), result.getBalance());
            var inOrder = Mockito.inOrder(repository);
            inOrder.verify(repository).save(cardModel);
            inOrder.verifyNoMoreInteractions();
        }
    }

}
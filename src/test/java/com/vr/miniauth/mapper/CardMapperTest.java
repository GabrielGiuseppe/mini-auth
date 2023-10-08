package com.vr.miniauth.mapper;

import com.vr.miniauth.builder.TestCardEntityBuilder;
import com.vr.miniauth.builder.TestCardRequestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.vr.miniauth.mapper.CardMapper.mapToModel;
import static com.vr.miniauth.mapper.CardMapper.mapToResponse;
import static com.vr.miniauth.utils.CryptUtils.decryptPassword;
import static com.vr.miniauth.utils.MaskUtils.removeCardNumberMask;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class CardMapperTest {

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Nested
    class MapToModelTest{
        @Test
        void testShouldMapToModel(){
            //Arrange
            final var cardRequest = new TestCardRequestBuilder().build();
            //Act
            final var resultModel = mapToModel(cardRequest);
            //Assert
            assertEquals(cardRequest.getCardNumber(), removeCardNumberMask(resultModel.getNumber()));
            assertEquals(cardRequest.getPassword(), decryptPassword(resultModel.getPassword()));
        }
    }
    @Nested
    class MapToResponseTest{
        @Test
        void testShouldMapToModel(){
            //Arrange
            final var cardModel = new TestCardEntityBuilder().build();
            //Act
            final var resultResponse = mapToResponse(cardModel);
            //Assert
            assertEquals(cardModel.getNumber(), resultResponse.getCardNumber());
            assertEquals(decryptPassword(cardModel.getPassword()), resultResponse.getPassword());
        }
    }

}
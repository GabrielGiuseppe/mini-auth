package com.vr.miniauth.controller;

import com.vr.miniauth.builder.TestTransactionRequestBuilder;
import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.service.TransactionService;
import com.vr.miniauth.utils.ProjectConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.inOrder;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @InjectMocks
    TransactionController controller;

    @Mock
    TransactionService service;

    @BeforeEach
    void setUp(){
        openMocks(this);
    }

    @Nested
    class ApproveTransactionTest{

        @Test
        void testShouldApproveTransaction(){
            //Arrange
            final var request = new TestTransactionRequestBuilder().build();
            final var baseResponse = new BaseReturn<>(ProjectConstants.OK_TRANSACTIUON);

            Mockito.when(service.approveTransaction(request)).thenReturn(baseResponse);

            //Act
            controller.approveTransaction(request);

            //Assert
            var inOrder = inOrder(service);
            inOrder.verify(service).approveTransaction(request);
            inOrder.verifyNoMoreInteractions();
        }
    }

}
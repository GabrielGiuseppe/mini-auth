package com.vr.miniauth.controller;

import com.vr.miniauth.common.controller.BaseController;
import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.request.TransactionRequest;
import com.vr.miniauth.response.TransactionResponse;
import com.vr.miniauth.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransactionController extends BaseController {

    @Autowired
    TransactionService service;

    @PostMapping(produces = PRODUCES_JSON, consumes = CONSUMES_JSON)
    public BaseReturn<String> approveTransaction(@RequestBody TransactionRequest request){
        return service.approveTransaction(request);
    }
}

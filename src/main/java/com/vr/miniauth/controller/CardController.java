package com.vr.miniauth.controller;


import com.vr.miniauth.common.controller.BaseController;
import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.request.CardRequest;
import com.vr.miniauth.response.CardResponse;
import com.vr.miniauth.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartoes")
public class CardController extends BaseController {

    @Autowired
    CardService service;

    @PostMapping(produces = PRODUCES_JSON, consumes = CONSUMES_JSON)
    public BaseReturn<CardResponse> create(@RequestBody CardRequest request){
        return service.create(request);
    }

    @GetMapping(path = {"/{numeroCartao}"},produces = PRODUCES_JSON, consumes = CONSUMES_ALL)
    public BaseReturn<BigDecimal> getBalance(@PathVariable(name = "numeroCartao") String cardNumber){
        return service.getBalance(cardNumber);
    }
}

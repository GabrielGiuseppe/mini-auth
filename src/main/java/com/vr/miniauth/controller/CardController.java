package com.vr.miniauth.controller;


import com.vr.miniauth.common.controller.BaseController;
import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.request.CardRequest;
import com.vr.miniauth.response.CardResponse;
import com.vr.miniauth.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

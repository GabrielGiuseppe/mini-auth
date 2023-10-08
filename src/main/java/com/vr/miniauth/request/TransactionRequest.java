package com.vr.miniauth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest {

    @JsonProperty("numeroCartao")
    private String cardNumber;

    @JsonProperty("senhaCartao")
    private String password;

    @JsonProperty("valor")
    private BigDecimal value;
}

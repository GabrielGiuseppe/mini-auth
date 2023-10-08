package com.vr.miniauth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardRequest {

    @JsonProperty("numeroCartao")
    private String cardNumber;

    @JsonProperty("senha")
    private String password;
}

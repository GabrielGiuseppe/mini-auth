package com.vr.miniauth.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("senha")
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("numeroCartao")
    private String cardNumber;

    public CardResponse withCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CardResponse withPassword(String decryptPassword) {
        this.password = decryptPassword;
        return this;
    }
}

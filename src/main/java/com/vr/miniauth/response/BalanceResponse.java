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
public class BalanceResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("resposta")
    private String response;
}

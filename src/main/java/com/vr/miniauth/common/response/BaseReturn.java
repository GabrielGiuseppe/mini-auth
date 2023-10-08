package com.vr.miniauth.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseReturn<T> implements Serializable {

    private static final long serialVersionUID = -3313325525077947576L;

    public BaseReturn() {
        this.success = true;
        this.errorMessage = null;
        this.description = null;
        this.rejectValue = null;
        this.field = null;
        this.errorCode = null;
        this.error = null;
        this.data = null;
    }

    public BaseReturn(T data) {
        this.success = true;
        this.errorMessage = null;
        this.description = null;
        this.rejectValue = null;
        this.field = null;
        this.errorCode = null;
        this.error = null;
        this.data = data;
    }

    @JsonProperty("sucesso")
    public Boolean success;

    @JsonProperty("codigoErro")
    public Integer errorCode;

    @JsonProperty("erro")
    public String error;

    @JsonProperty("campo")
    public String field;

    @JsonProperty("valorRejeitado")
    public Object rejectValue;

    @JsonProperty("mensagemErro")
    public String errorMessage;

    @JsonProperty("descricao")
    public String description;

    @JsonProperty("dados")
    public T data;
}

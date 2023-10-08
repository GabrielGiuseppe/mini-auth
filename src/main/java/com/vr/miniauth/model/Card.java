package com.vr.miniauth.model;

import com.vr.miniauth.common.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.math.BigDecimal;
@Getter
@Setter
@Entity
@Table(name = "card")
public class Card extends BaseModel {

    @Column(name = "number")
    private String number;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @PrePersist
    public void initializeUUID() {
        balance = new BigDecimal(500.00);
    }

    public Card withNumber(String cardNumber) {
        this.number = cardNumber;
        return this;
    }

    public Card withPass(String encryptPassword) {
        this.password = encryptPassword;
        return this;
    }

    public Card withBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}

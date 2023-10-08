package com.vr.miniauth.repository;

import com.vr.miniauth.model.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardRepository extends CrudRepository<Card, Long> {

    Optional<Card> getByNumber(String applyCardNumberMaskcardNumber);
}

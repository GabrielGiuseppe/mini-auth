package com.vr.miniauth.service;

import com.vr.miniauth.common.response.BaseReturn;
import com.vr.miniauth.request.TransactionRequest;

public interface TransactionService {
    BaseReturn<String> approveTransaction(TransactionRequest request);
}

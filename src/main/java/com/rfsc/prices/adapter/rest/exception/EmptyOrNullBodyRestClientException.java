package com.rfsc.prices.adapter.rest.exception;

import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.config.exception.GenericException;

public final class EmptyOrNullBodyRestClientException extends GenericException {

    public EmptyOrNullBodyRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}

package com.rfsc.prices.adapter.rest.exception;

import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.config.exception.GenericException;

public final class NotFoundRestClientException extends GenericException {

    public NotFoundRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }
}

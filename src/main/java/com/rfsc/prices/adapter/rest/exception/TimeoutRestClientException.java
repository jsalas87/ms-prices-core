package com.rfsc.prices.adapter.rest.exception;

import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.config.exception.GenericException;

public final class TimeoutRestClientException extends GenericException {

    public TimeoutRestClientException(ErrorCode errorCode) {
        super(errorCode);
    }

}

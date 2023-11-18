package com.rfsc.prices.adapter.rest.exception;

import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.config.exception.GenericException;

public final class RestClientGenericException extends GenericException {

    public RestClientGenericException(ErrorCode errorCode) {
        super(errorCode);
    }

}

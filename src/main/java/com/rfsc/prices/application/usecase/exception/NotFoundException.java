package com.rfsc.prices.application.usecase.exception;

import com.rfsc.prices.config.ErrorCode;
import com.rfsc.prices.config.exception.GenericException;

public final class NotFoundException extends GenericException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

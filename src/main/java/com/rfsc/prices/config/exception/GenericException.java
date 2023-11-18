package com.rfsc.prices.config.exception;

import com.rfsc.prices.config.ErrorCode;
import org.apache.commons.lang3.StringUtils;

public abstract class GenericException extends RuntimeException {

    private static final String SPACE = StringUtils.SPACE;
    private static final String COMMA = ",";
    private final ErrorCode errorCode;

    protected GenericException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
    }

    public ErrorCode getCode() {
        return this.errorCode;
    }

    private static String buildMessage(String message, ErrorCode errorCode) {
        return errorCode.getReasonPhrase() + COMMA + SPACE + message;
    }

}

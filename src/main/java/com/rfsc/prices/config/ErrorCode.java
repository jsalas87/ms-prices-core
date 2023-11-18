package com.rfsc.prices.config;

public class ErrorCode {

    public static ErrorCode INTERNAL_ERROR = new ErrorCode(100, "Error interno del servidor");

    public static ErrorCode NOT_FOUNT = new ErrorCode(101, "Dato no encontrado");

    private final int value;
    private final String reasonPhrase;

    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}

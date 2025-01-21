package com.chameleon.cilicili.exception;

public class KaptchaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KaptchaException() {
        super();
    }

    public KaptchaException(String message) {
        super(message);
    }

    public KaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public KaptchaException(Throwable cause) {
        super(cause);
    }
    
}

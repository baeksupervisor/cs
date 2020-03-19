package com.baeksupervisor.ticket.exception;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/13
 */
public class NeedToLoginException extends RuntimeException {
    public NeedToLoginException() {
        super();
    }

    public NeedToLoginException(String message) {
        super(message);
    }

    public NeedToLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedToLoginException(Throwable cause) {
        super(cause);
    }

    protected NeedToLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

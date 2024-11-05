package org.example.msuser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorConstants {
    USER_NOTFOUND_EXCEPTION("User Not Found", "NOT_FOUND"),
    UNEXPECTED_EXCEPTION("Unexpected Error", "UNEXPECTED_ERROR");
    private final String message;
    private final String code;
}

package io.github.hoangtuyen04work.social_backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends Exception {
    private ErrorCode errorCode;
    public AppException(ErrorCode error){
        super();
        this.errorCode = error;
    }
}

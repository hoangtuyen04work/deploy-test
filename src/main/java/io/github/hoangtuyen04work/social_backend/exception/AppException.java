package io.github.hoangtuyen04work.social_backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AppException extends Exception {
    private ErrorCode errorCode;
    public AppException(ErrorCode error){
        super();
        this.errorCode = error;
    }
}

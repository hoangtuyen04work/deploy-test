package io.github.hoangtuyen04work.social_backend.exception;

import io.github.hoangtuyen04work.social_backend.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<Object> exception(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setStatus(errorCode.getStatus());
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(apiResponse);
    }
}

package io.github.hoangtuyen04work.social_backend.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}

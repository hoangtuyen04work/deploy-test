package io.github.hoangtuyen04work.social_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String userId;
    String userName;
    String password;
    String email;
    String phone;
    String bio;
    Date dob;
    String address;
}

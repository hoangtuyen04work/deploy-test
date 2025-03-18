package io.github.hoangtuyen04work.social_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String customId;
    String userName;
    String password;
    String email;
    String phone;
    String bio;
    Date dob;
    String address;
}

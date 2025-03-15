package io.github.hoangtuyen04work.social_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class RefreshTokenEntity extends FormEntity{

    @Column(nullable = false)
    String refreshToken;

    @Column(nullable = false)
    LocalDate expirationTime;
}
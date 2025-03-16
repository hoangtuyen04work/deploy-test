package io.github.hoangtuyen04work.social_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "resfresh_token")
public class RefreshTokenEntity extends FormEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @Column(nullable = false)
    String refreshToken;

    @Column(nullable = false)
    LocalDate expirationTime;
}
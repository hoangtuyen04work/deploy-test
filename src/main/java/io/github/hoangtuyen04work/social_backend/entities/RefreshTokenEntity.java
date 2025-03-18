package io.github.hoangtuyen04work.social_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "resfresh_token")
public class RefreshTokenEntity extends FormEntity{

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    UserEntity user;

    @Column(nullable = false)
    String refreshToken;

    @Column(nullable = false)
    Date expirationTime;
}
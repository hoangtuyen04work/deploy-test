package io.github.hoangtuyen04work.social_backend.entities;

import io.github.hoangtuyen04work.social_backend.enums.Friendship;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "friendship")
public class FriendshipEntity extends FormEntity{
    @ManyToOne
    @JoinColumn(name = "user1", nullable = false)
    UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2", nullable = false)
    UserEntity user2;

    @Enumerated(EnumType.STRING)
    Friendship friendship;
}

package io.github.hoangtuyen04work.social_backend.entities;

import io.github.hoangtuyen04work.social_backend.enums.Reaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "post_reaction")
public class PostReactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    PostEntity post;
}

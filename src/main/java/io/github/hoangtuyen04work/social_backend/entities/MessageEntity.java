package io.github.hoangtuyen04work.social_backend.entities;

import io.github.hoangtuyen04work.social_backend.enums.Reaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "messages")
public class MessageEntity extends FormEntity {
    String content;
    String imageLink;
    @Enumerated(EnumType.STRING)
    Reaction reaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    ConversationEntity conversation;

    @ManyToOne
    @JoinColumn(name = "group_id")
    GroupEntity group;
}

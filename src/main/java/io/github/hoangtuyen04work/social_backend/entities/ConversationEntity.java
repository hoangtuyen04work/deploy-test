package io.github.hoangtuyen04work.social_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Set;
@Builder

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "conversations")
public class ConversationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @LastModifiedDate
    Instant lastMessageAt;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    UserEntity user2;

    @OneToMany(mappedBy = "conversation")
    Set<MessageEntity> messages;
}

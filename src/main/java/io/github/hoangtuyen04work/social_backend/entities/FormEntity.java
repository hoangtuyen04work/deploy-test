package io.github.hoangtuyen04work.social_backend.entities;


import io.github.hoangtuyen04work.social_backend.enums.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class FormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    @CreatedDate
    @Column(updatable = false)
    Instant creationDate;

    @LastModifiedDate
    Instant modifiedDate;

    Instant deleteDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    State state;
}

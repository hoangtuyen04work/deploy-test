package io.github.hoangtuyen04work.social_backend.entities;

import io.github.hoangtuyen04work.social_backend.enums.Reaction;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
public class CommentEntity extends FormEntity{
    @Column(nullable = false)
    String content;
    String imageLink;
    @Enumerated(EnumType.STRING)
    Reaction reaction;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    List<CommentEntity> replies;

    @ManyToOne
    @JoinColumn(name = "post_id")
    PostEntity post;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    CommentEntity parent;

    @ManyToMany
    @JoinTable(
            name = "user_comment_tag",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<UserEntity> taggedUsers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
}

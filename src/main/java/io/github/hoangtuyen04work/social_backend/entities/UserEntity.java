package io.github.hoangtuyen04work.social_backend.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class UserEntity extends FormEntity{
    @Column(nullable = false)
    String userId;
    @Column(nullable = false)
    String userName;
    @Column(nullable = false)
    String password;
    String email;
    String phone;
    String bio;
    Date dob;
    String address;

    @ManyToMany(mappedBy = "user")
    Set<RoleEntity> roles;
}

//n - n User
//1 - n Post
//n - n Post (like, comment)
//1 - n Comment
//n - n Comment(like, comment)
//1 - n Message
//n -n Group


package io.github.hoangtuyen04work.social_backend.repositories;

import io.github.hoangtuyen04work.social_backend.entities.FriendshipEntity;
import io.github.hoangtuyen04work.social_backend.enums.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepo extends JpaRepository<FriendshipEntity, String> {
    @Query(value = "SELECT * FROM FriendshipEntity f " +
            "WHERE f.user1.id = :userId AND f.user2.id = :friendId AND f.friendship = :friendship")
    boolean existsByUserIdAndFriendIdAndFriendship(String userId, String friendId, Friendship friendship);
}

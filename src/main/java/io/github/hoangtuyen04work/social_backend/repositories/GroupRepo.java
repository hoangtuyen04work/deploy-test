package io.github.hoangtuyen04work.social_backend.repositories;

import io.github.hoangtuyen04work.social_backend.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository< GroupEntity, String> {
}

package io.github.hoangtuyen04work.social_backend.repositories;

import io.github.hoangtuyen04work.social_backend.entities.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshTokenEntity, String> {
}

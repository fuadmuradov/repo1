package org.example.msuser.repositoy;

import org.example.msuser.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = {"addresses"})
    Optional<UserEntity> findByIdAndIsDeletedFalse(Long id);
    List<UserEntity> findAllByIsDeletedFalse();
}

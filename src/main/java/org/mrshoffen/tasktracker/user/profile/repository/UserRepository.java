package org.mrshoffen.tasktracker.user.profile.repository;


import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmailIgnoreCase(String email);

}

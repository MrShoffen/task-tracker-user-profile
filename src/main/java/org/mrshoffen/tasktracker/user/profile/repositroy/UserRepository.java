package org.mrshoffen.tasktracker.user.profile.repositroy;


import org.mrshoffen.tasktracker.user.profile.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);

}

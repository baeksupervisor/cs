package com.baeksupervisor.ticket.repository;

import com.baeksupervisor.ticket.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/08
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

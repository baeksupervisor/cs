package com.baeksupervisor.ticket.repository;

import com.baeksupervisor.ticket.persistence.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/15
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}

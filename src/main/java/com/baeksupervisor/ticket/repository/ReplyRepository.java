package com.baeksupervisor.ticket.repository;

import com.baeksupervisor.ticket.persistence.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/20
 */
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}

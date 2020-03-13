package com.baeksupervisor.ticket.repository;

import com.baeksupervisor.ticket.persistence.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
    Optional<TicketType> findByName(String name);
}

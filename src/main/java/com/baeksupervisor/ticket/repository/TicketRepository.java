package com.baeksupervisor.ticket.repository;

import com.baeksupervisor.ticket.persistence.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}

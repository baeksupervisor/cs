package com.baeksupervisor.ticket.service;

import com.baeksupervisor.ticket.exception.ResourceNotFoundException;
import com.baeksupervisor.ticket.persistence.Comment;
import com.baeksupervisor.ticket.persistence.Ticket;
import com.baeksupervisor.ticket.repository.CommentRepository;
import com.baeksupervisor.ticket.repository.TicketRepository;
import com.baeksupervisor.ticket.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/15
 */
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Comment saveComment(Long ticketId, Comment comment) {
        comment.setCreator(userRepository.findByEmail("shbaek159@gmail.com").get());
        commentRepository.save(comment);

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(ResourceNotFoundException::new);
        ticket.getComments().add(comment);

        return comment;
    }
}

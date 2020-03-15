package com.baeksupervisor.ticket.controller;

import com.baeksupervisor.ticket.exception.ResourceNotFoundException;
import com.baeksupervisor.ticket.model.TicketView;
import com.baeksupervisor.ticket.persistence.Attachment;
import com.baeksupervisor.ticket.persistence.Comment;
import com.baeksupervisor.ticket.persistence.Ticket;
import com.baeksupervisor.ticket.repository.CommentRepository;
import com.baeksupervisor.ticket.repository.TicketRepository;
import com.baeksupervisor.ticket.repository.TicketTypeRepository;
import com.baeksupervisor.ticket.repository.UserRepository;
import com.baeksupervisor.ticket.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Slf4j
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketTypeRepository csTypeRepository;
    private final TicketRepository ticketRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public TicketController(TicketService ticketService, TicketTypeRepository csTypeRepository, TicketRepository ticketRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.ticketService = ticketService;
        this.csTypeRepository = csTypeRepository;
        this.ticketRepository = ticketRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String getTickets(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets";
    }

    @GetMapping("/{id}")
    public String getTicket(@PathVariable(value = "id") Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.addAttribute("ticket", TicketView.getInstance(ticket));
        return "ticket";
    }

    @GetMapping("/register")
    public String registerTicket(Model model) {
        model.addAttribute("managers", userRepository.findAll());
        model.addAttribute("types", csTypeRepository.findAll());
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("file", null);
        return "register_ticket";
    }

    @PostMapping("")
    public String postTicket(Ticket ticket) throws IOException {
        log.info("{}", ticket);
        ticket.setCreator(userRepository.findByEmail("shbaek159@gmail.com").get());
        ticket.setAttachments(ticket.getMultipartFiles()
                .stream()
                .filter(o -> !o.isEmpty())
                .map(Attachment::getInstance)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @ResponseBody
    @PostMapping("/{id}/comments")
    public Comment postComment(@PathVariable(value = "id") Long ticketId, @RequestBody Comment comment) {
        return ticketService.saveComment(ticketId, comment);
    }
}

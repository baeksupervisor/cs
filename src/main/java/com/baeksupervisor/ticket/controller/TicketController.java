package com.baeksupervisor.ticket.controller;

import com.baeksupervisor.ticket.exception.ResourceNotFoundException;
import com.baeksupervisor.ticket.model.TicketView;
import com.baeksupervisor.ticket.persistence.Ticket;
import com.baeksupervisor.ticket.repository.TicketRepository;
import com.baeksupervisor.ticket.repository.TicketTypeRepository;
import com.baeksupervisor.ticket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Slf4j
@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketTypeRepository csTypeRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketController(TicketTypeRepository csTypeRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.csTypeRepository = csTypeRepository;
        this.ticketRepository = ticketRepository;
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
//        ticket.setFile(ticket.getUploadFile().getBytes());
        ticket.getAttachments().forEach(o -> {
            try {
                o.setBlob(o.getMultipartFile().getBytes());
                o.setOriginalFilename(o.getMultipartFile().getOriginalFilename());
                o.setFileSize(o.getMultipartFile().getSize());
                o.setMimeType(o.getMultipartFile().getContentType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }
}

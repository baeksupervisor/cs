package com.baeksupervisor.ticket;

import com.baeksupervisor.ticket.persistence.Comment;
import com.baeksupervisor.ticket.persistence.Ticket;
import com.baeksupervisor.ticket.persistence.TicketType;
import com.baeksupervisor.ticket.persistence.User;
import com.baeksupervisor.ticket.repository.TicketRepository;
import com.baeksupervisor.ticket.repository.TicketTypeRepository;
import com.baeksupervisor.ticket.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final UserRepository userRepository;

    public Application(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<TicketType> ticketTypes = new ArrayList<>();
        ticketTypes.add(TicketType.of("재고문의"));
        ticketTypes.add(TicketType.of("포인트문의"));
        ticketTypes.add(TicketType.of("가입문의"));
        ticketTypes.add(TicketType.of("인증문의"));
        ticketTypes.add(TicketType.of("주문문의"));
        ticketTypes.add(TicketType.of("배송문의"));
        ticketTypes.add(TicketType.of("교환문의"));
        ticketTypes.add(TicketType.of("환불문의"));
        ticketTypes.add(TicketType.of("반품문의"));
        ticketTypes.add(TicketType.of("결제오류"));
        ticketTypes.add(TicketType.of("포인트오류"));
        ticketTypes.add(TicketType.of("가입오류"));
        ticketTypes.add(TicketType.of("인증오류"));
        ticketTypeRepository.saveAll(ticketTypes);

        List<User> users = new ArrayList<>();
        users.add(User.of("shbaek159@gmail.com", "shbaek"));
        users.add(User.of("user1@email.com", "user1"));
        userRepository.saveAll(users);

        List<Comment> comments = new ArrayList<>();
        comments.add(Comment.of("first\n이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.", users.get(0)));
        comments.add(Comment.of("second\n이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.이것은 댓글이지.", users.get(0)));

        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = Ticket.of("재고알랴줌?", userRepository.findByEmail("user1@email.com").get(), ticketTypeRepository.findByName("재고문의").orElse(null));
        ticket.setManager(userRepository.findByEmail("shbaek159@gmail.com").get());
        ticket.setContent("재고안알랴줌!~!");
        ticket.setComments(comments);
        tickets.add(ticket);
        tickets.add(Ticket.of("포인트안줌?", userRepository.findByEmail("user1@email.com").get(), ticketTypeRepository.findByName("포인트문의").orElse(null)));
        ticketRepository.saveAll(tickets);
    }
}

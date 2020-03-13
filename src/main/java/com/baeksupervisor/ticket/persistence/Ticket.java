package com.baeksupervisor.ticket.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private TicketType ticketType;

    private String title;

    private String content;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToOne(optional = false)
    private User creator;

    @ManyToOne
    private User manager;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Ticket of(String title, User user, TicketType ticketType) {
        Ticket ticket = new Ticket();
        ticket.ticketType = ticketType;
        ticket.title = title;
        ticket.creator = user;
        return ticket;
    }
}

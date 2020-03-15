package com.baeksupervisor.ticket.model;

import com.baeksupervisor.ticket.persistence.Ticket;
import com.baeksupervisor.ticket.persistence.TicketType;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/13
 */
@Getter
public class TicketView implements Serializable {

    private Long id;

    private TicketType ticketType;

    private String title;

    private String content;

    private String creator;

    private String manager;

    private List<TicketAttachmentView> attachments;

    private List<TicketCommentView> comments;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static TicketView getInstance(Ticket ticket) {
        TicketView view = new TicketView();
        view.id = ticket.getId();
        view.ticketType = ticket.getTicketType();
        view.title = ticket.getTitle();
        view.content = ticket.getContent();
        view.creator = ticket.getCreator().getEmail();
        if (Objects.nonNull(ticket.getManager())) {
            view.manager = ticket.getManager().getEmail();
        }
        view.createdAt = ticket.getCreatedAt();
        view.updatedAt = ticket.getUpdatedAt();
        view.attachments = ticket.getAttachments()
                .stream()
                .map(TicketAttachmentView::getInstance)
                .filter(o -> Objects.nonNull(o.getFile()))
                .collect(Collectors.toList());
        view.comments = ticket.getComments()
                .stream()
                .map(TicketCommentView::getInstance)
                .collect(Collectors.toList());
        return view;
    }
}

package com.baeksupervisor.ticket.model;

import com.baeksupervisor.ticket.persistence.Comment;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/15
 */
@Getter
public class TicketCommentView implements Serializable {

    private Long id;

    private String message;

    private String creator;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static TicketCommentView getInstance(Comment comment) {
        TicketCommentView view = new TicketCommentView();
        view.id = comment.getId();
        view.message = comment.getMessage();
        view.creator = comment.getCreator().getEmail();
        view.createdAt = comment.getCreatedAt();
        return view;
    }
}

package com.baeksupervisor.ticket.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/15
 */
@Getter
@NoArgsConstructor
@Entity
public class Comment implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String message;

    @Setter
    @ManyToOne(optional = false)
    private User creator;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Comment of(String message, User user) {
        Comment comment = new Comment();
        comment.message = message;
        comment.creator = user;
        return comment;
    }
}

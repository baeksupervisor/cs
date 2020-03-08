package com.baeksupervisor.cs.persistence;

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
 * Since 2020/03/06
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Board implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private CsType csType;

    private String title;

    private String content;

    @ManyToOne(optional = false)
    private User creator;

    @ManyToOne
    private User manager;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public static Board of(String title, User user, CsType csType) {
        Board board = new Board();
        board.csType = csType;
        board.title = title;
        board.creator = user;
        return board;
    }
}

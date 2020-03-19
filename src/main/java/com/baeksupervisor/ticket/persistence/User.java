package com.baeksupervisor.ticket.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/08
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String name;

    public static User of(String email, String name, String password) {
        User user = new User();
        user.email = email;
        user.name = name;
        user.password = password;
        return user;
    }
}

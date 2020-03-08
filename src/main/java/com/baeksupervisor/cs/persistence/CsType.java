package com.baeksupervisor.cs.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
public class CsType {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static CsType of(String name) {
        CsType csSuperType = new CsType();
        csSuperType.name = name;
        return csSuperType;
    }
}

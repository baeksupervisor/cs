package com.baeksupervisor.cs.repository;

import com.baeksupervisor.cs.persistence.CsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
public interface CsTypeRepository extends JpaRepository<CsType, Long> {
    Optional<CsType> findByName(String name);
}

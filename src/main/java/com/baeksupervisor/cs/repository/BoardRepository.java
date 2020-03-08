package com.baeksupervisor.cs.repository;

import com.baeksupervisor.cs.persistence.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
}

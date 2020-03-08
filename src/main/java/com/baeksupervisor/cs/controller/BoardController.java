package com.baeksupervisor.cs.controller;

import com.baeksupervisor.cs.persistence.Board;
import com.baeksupervisor.cs.repository.CsTypeRepository;
import com.baeksupervisor.cs.repository.UserRepository;
import com.baeksupervisor.cs.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/06
 */
@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    private final CsTypeRepository csTypeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardController(CsTypeRepository csTypeRepository, BoardRepository boardRepository, UserRepository userRepository) {
        this.csTypeRepository = csTypeRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String boards(Model model) {

        model.addAttribute("boards", boardRepository.findAll());
        return "boards";
    }

    @GetMapping("/register")
    public String registerBoards(Model model) {
        model.addAttribute("managers", userRepository.findAll());
        model.addAttribute("types", csTypeRepository.findAll());
        model.addAttribute("board", new Board());
        return "register_board";
    }

    @PostMapping("")
    public String postBoard(Board board) {
        log.info("{}", board);
        board.setCreator(userRepository.findByEmail("shbaek@temco.io").get());
        boardRepository.save(board);
        return "redirect:/boards";
    }
}

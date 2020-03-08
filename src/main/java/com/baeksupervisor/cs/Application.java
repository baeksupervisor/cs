package com.baeksupervisor.cs;

import com.baeksupervisor.cs.persistence.Board;
import com.baeksupervisor.cs.persistence.CsType;
import com.baeksupervisor.cs.persistence.User;
import com.baeksupervisor.cs.repository.CsTypeRepository;
import com.baeksupervisor.cs.repository.UserRepository;
import com.baeksupervisor.cs.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final BoardRepository boardRepository;
    private final CsTypeRepository csTypeRepository;
    private final UserRepository userRepository;

    public Application(BoardRepository boardRepository, CsTypeRepository csTypeRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.csTypeRepository = csTypeRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<CsType> csTypes = new ArrayList<>();
        csTypes.add(CsType.of("재고문의"));
        csTypes.add(CsType.of("포인트문의"));
        csTypes.add(CsType.of("가입문의"));
        csTypes.add(CsType.of("인증문의"));
        csTypes.add(CsType.of("주문문의"));
        csTypes.add(CsType.of("배송문의"));
        csTypes.add(CsType.of("교환문의"));
        csTypes.add(CsType.of("환불문의"));
        csTypes.add(CsType.of("반품문의"));
        csTypes.add(CsType.of("결제오류"));
        csTypes.add(CsType.of("포인트오류"));
        csTypes.add(CsType.of("가입오류"));
        csTypes.add(CsType.of("인증오류"));
        csTypeRepository.saveAll(csTypes);

        List<User> users = new ArrayList<>();
        users.add(User.of("shbaek159@gmail.com", "shbaek"));
        users.add(User.of("user1@email.com", "user1"));
        userRepository.saveAll(users);

        List<Board> boards = new ArrayList<>();
        Board board1 = Board.of("재고알랴줌?", userRepository.findByEmail("user1@email.com").get(), csTypeRepository.findByName("재고문의").orElse(null));
        board1.setManager(userRepository.findByEmail("shbaek159@gmail.com").get());
        boards.add(board1);
        boards.add(Board.of("포인트안줌?", userRepository.findByEmail("user1@email.com").get(), csTypeRepository.findByName("포인트문의").orElse(null)));
        boardRepository.saveAll(boards);
    }
}

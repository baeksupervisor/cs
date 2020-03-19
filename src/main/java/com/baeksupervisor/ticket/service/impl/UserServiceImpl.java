package com.baeksupervisor.ticket.service.impl;

import com.baeksupervisor.ticket.exception.ResourceNotFoundException;
import com.baeksupervisor.ticket.persistence.User;
import com.baeksupervisor.ticket.repository.UserRepository;
import com.baeksupervisor.ticket.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOneByEmail(String email) throws Exception {
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }
}

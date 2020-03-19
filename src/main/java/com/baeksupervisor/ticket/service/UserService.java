package com.baeksupervisor.ticket.service;

import com.baeksupervisor.ticket.persistence.User;

public interface UserService {

    User findOneByEmail(String email) throws Exception;
}

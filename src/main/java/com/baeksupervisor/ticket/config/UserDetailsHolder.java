package com.baeksupervisor.ticket.config;

import com.baeksupervisor.ticket.exception.NeedToLoginException;
import com.baeksupervisor.ticket.model.SupportUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Youngil.Park
 * Since 2019-05-07
 */
public class UserDetailsHolder {

    public static SupportUserDetails getUserDetails() throws Exception {

        SupportUserDetails userDetails;
        try {
            userDetails = (SupportUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            throw new NeedToLoginException();
        }

        if(userDetails == null)
            throw new NeedToLoginException();

        return userDetails;
    }
}

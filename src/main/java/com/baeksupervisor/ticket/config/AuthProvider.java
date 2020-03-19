package com.baeksupervisor.ticket.config;

import com.baeksupervisor.ticket.model.SupportUserDetails;
import com.baeksupervisor.ticket.persistence.User;
import com.baeksupervisor.ticket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = new User();
        SupportUserDetails userDetails = new SupportUserDetails();
        String email = (String)authentication.getPrincipal();
        String password = (String)authentication.getCredentials();

        try {
            user = userService.findOneByEmail(email);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadCredentialsException("0002");
        }

        String digestPassword = DigestUtils.sha256Hex(password);

        if(!user.getPassword().equals(digestPassword)) {
            throw new BadCredentialsException("0001");
        }

        userDetails.setUser(user);

        return new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials(), authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}

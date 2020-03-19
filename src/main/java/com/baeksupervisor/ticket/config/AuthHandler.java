package com.baeksupervisor.ticket.config;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler  implements AuthenticationSuccessHandler, AuthenticationFailureHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();

        out.write("{\"responseCode\": \"0000\", \"responseMessage\": \"login success.\"}");
        out.close();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();

        if( exception.getMessage().equals("0001") ){
            out.write("{\"responseCode\": \"0001\", \"responseMessage\": \"incorrect password.\"}");
        } else if(exception.getMessage().equals("0002")){
            out.write("{\"responseCode\": \"0002\", \"responseMessage\": \"user not found.\"}");
        } else {
            out.write("{\"responseCode\": \"0009\", \"responseMessage\": \"error....\"}");
        }

        out.close();

    }

}

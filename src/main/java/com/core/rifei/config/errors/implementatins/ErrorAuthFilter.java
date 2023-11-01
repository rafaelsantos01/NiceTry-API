package com.core.rifei.config.errors.implementatins;

import com.core.rifei.config.errors.implementatins.dto.ErrorAuthDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ErrorAuthFilter {

  @Autowired
  private MessageSource messageSource;

  public void tokenExpired(HttpServletResponse response) throws ServletException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    String message = messageSource.getMessage(
      "TokenExpired",
      null,
      LocaleContextHolder.getLocale());

    ErrorAuthDTO errorAuthDTO = new ErrorAuthDTO("token.expired", message);

    String json = objectMapper.writeValueAsString(errorAuthDTO);

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(json);
    response.getWriter().flush();
  }

  public void errorAuth(HttpServletResponse response) throws ServletException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    String message = messageSource.getMessage(
      "AuthInsuffiError",
      null,
      LocaleContextHolder.getLocale());

    ErrorAuthDTO errorAuthDTO = new ErrorAuthDTO("credentials", message);

    String json = objectMapper.writeValueAsString(errorAuthDTO);

    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(json);
    response.getWriter().flush();
  }

}

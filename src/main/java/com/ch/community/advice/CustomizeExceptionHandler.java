package com.ch.community.advice;

import com.alibaba.fastjson.JSON;
import com.ch.community.Exception.CustomizeErrorCode;
import com.ch.community.Exception.CustomizeException;
import com.ch.community.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
  @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
    HttpStatus status = getStatus(request);
    String contentType = request.getContentType();
    if ("application/json".equals(contentType)) {
      ResultDTO resultDTO = new ResultDTO();
      if (ex instanceof CustomizeException) {
        resultDTO = resultDTO.errorOf((CustomizeException)ex);
      } else {
        resultDTO = resultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
      }
      try {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(resultDTO));
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    } else {

      if (ex instanceof CustomizeException) {
        model.addAttribute("message", ex.getMessage());
      } else {
        model.addAttribute("message", CustomizeErrorCode.SYS_ERROR);
      }
      return new ModelAndView("error");
    }
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }
}

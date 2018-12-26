package com.epam.viktoryia.springdatabase.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView employeeDuplicate(DuplicateKeyException ex) {
        System.out.println("----Caught DuplicateKeyException----");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("exception");
        return mav;
    }

    @ResponseBody
    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(ClassNotFoundException ex) {
        return "not_found";
    }

}
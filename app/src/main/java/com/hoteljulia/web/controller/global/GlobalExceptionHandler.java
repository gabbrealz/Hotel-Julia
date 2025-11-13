package com.hoteljulia.web.controller.global;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException error, Model model) {
        return  "redirect:/error?message=The+page+you+are+looking+" +
                "for+might+have+been+removed%2C+had+its+name+" +
                "changed%2C+or+is+currently+unavailable.";
    }
}

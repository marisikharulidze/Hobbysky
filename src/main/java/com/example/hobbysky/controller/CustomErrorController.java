package com.example.hobbysky.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get the error status code
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // Handle different error status codes
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            // Handle 404 Not Found error
            return "error/404";
        } else {
            // Handle other errors
            return "error/general";
        }
    }

    public String getErrorPath() {
        return "/error";
    }
}
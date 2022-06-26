package org.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
public class ErrorController {

    public String handleError() {
        return "error";
    }
}

package com.glprojects.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MessageController {

    @Value("${app.message:Mensaje por defecto}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return this.message;
    }
}

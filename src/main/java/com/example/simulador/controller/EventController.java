package com.example.simulador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final SimpMessagingTemplate messagingTemplate;

    public EventController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping
    public ResponseEntity<Void> receiveEvent(@RequestBody Map<String, Object> eventData){
        System.out.println("Evento recibido desde moodle: " +eventData);
        messagingTemplate.convertAndSend("/topic/notifications", eventData);
        return ResponseEntity.ok().build();
    }
}

package com.example.reactive.controller;

import com.example.reactive.model.Message;
import com.example.reactive.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class which does something useful. Really.
 *
 * @author Marco Werner
 */
@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping(value = "")
    public Flux<Message> all() {
        return messageRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<Message> get(@PathVariable(value = "id") Long id) {
        return messageRepository.findById(id);
    }

    @PostMapping(value = "")
    public Mono<Message> create(Message message) {
        return messageRepository.createMessage(message);
    }
}

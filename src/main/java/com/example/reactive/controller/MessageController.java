package com.example.reactive.controller;

import com.example.reactive.model.Message;
import com.example.reactive.repository.MessageRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Class which does something useful. Really.
 *
 * @author Marco Werner
 */
@RestController
@RequestMapping(path = "/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Flux<Message> all() {
        return messageRepository.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<Message> get(@PathVariable(value = "id") Long id) {
        return messageRepository.findById(id);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mono<Message> create(@RequestBody @Valid Message message) {
        return messageRepository.createMessage(message);
    }
}

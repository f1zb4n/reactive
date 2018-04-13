package com.example.reactive.repository;

import com.example.reactive.model.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Class which does something useful. Really.
 *
 * @author Marco Werner
 */
@Component
public class MessageRepository {

    private static final Map<Long, Message> DATA = new HashMap<>();
    private static long ID_COUNTER = 1L;

    static {
        Stream.of("First Message", "Second Message")
                .forEach(text -> {
                            long id = ID_COUNTER++;
                            DATA.put(id, Message.builder().id(id).text(text).build());
                        }
                );
    }

    Flux<Message> findAll() {
        return Flux.fromIterable(DATA.values());
    }

    Mono<Message> findById(Long id) {
        return Mono.just(DATA.get(id));
    }

    Mono<Message> createMessage(Message Message) {
        long id = ID_COUNTER++;
        Message.setId(id);
        DATA.put(id, Message);
        return Mono.just(Message);
    }
}

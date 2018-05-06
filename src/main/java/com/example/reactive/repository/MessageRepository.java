package com.example.reactive.repository;

import com.example.reactive.model.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
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
        Stream.of("First Message", "Second Message", "Third Message")
                .forEach(text -> {
                            long id = ID_COUNTER++;
                            DATA.put(id, Message.builder().id(id).text(text).timestamp(LocalDateTime.now()).build());
                        }
                );
    }

    public Flux<Message> findAll() {
        return Flux.fromIterable(DATA.values());
    }

    public Mono<Message> findById(Long id) {
        return DATA.containsKey(id) ? Mono.just(DATA.get(id)) : Mono.empty();
    }

    public Mono<Message> createMessage(Message message) {
        long id = ID_COUNTER++;
        message.setId(id);
        message.setTimestamp(LocalDateTime.now());
        DATA.put(id, message);
        return Mono.just(message);
    }
}

package com.example.reactive;

import com.example.reactive.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReactiveApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAll() {
		this.webTestClient.get().uri("/messages")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
				.expectStatus().isOk()
				.expectBodyList(Message.class)
                .consumeWith(entityExchangeResult -> {
                    List<Message> messages = entityExchangeResult.getResponseBody();
                    assertThat(messages.size(), greaterThan(2));
                });
	}

	@Test
    public void testGetOne() {
	    this.webTestClient.get().uri("/messages/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Message.class)
                .hasSize(1);
    }

    @Test
    public void testPostOne() {
	    Message message = Message.builder().text("testPostOne").build();
        this.webTestClient.post().uri("/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(message), Message.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(4L)
                .jsonPath("$.text").isEqualTo(message.getText())
                .jsonPath("$.timestamp").isNotEmpty();
    }

}

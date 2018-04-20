package com.example.reactive;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.ipc.netty.http.server.HttpServer;

import java.io.IOException;

@ComponentScan
@EnableWebFlux
public class ReactiveApplication {

	private static final String DEFAULT_HOST = "localhost";
	private static final Integer DEFAULT_PORT = 8888;

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ReactiveApplication.class);

		HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();

		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
		HttpServer.create(DEFAULT_HOST, DEFAULT_PORT).newHandler(adapter).block();

        System.out.println("Press ENTER to exit.");
        System.in.read();
        System.out.println("Bye!");
    }
}

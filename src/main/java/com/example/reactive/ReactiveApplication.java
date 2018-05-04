package com.example.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveApplication {

//	private static final String DEFAULT_HOST = "localhost";
//	private static final Integer DEFAULT_PORT = 8888;

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(ReactiveApplication.class);
//
//		HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
//
//		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
//		HttpServer.create(DEFAULT_HOST, DEFAULT_PORT).newHandler(adapter).block();

        SpringApplication.run(ReactiveApplication.class);

//        System.out.println("Press ENTER to exit.");
//        System.in.read();
//        System.out.println("Bye!");
    }
}

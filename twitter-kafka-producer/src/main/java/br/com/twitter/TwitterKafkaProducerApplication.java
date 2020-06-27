package br.com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
@EnableWebFlux
@EnableAutoConfiguration
@ComponentScan("br.com.twitter")
public class TwitterKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterKafkaProducerApplication.class, args);
	}

}

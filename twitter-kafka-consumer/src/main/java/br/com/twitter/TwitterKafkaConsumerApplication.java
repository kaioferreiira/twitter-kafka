package br.com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EnableKafka
@ComponentScan("br.com.twitter")
public class TwitterKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterKafkaConsumerApplication.class, args);
	}

}

package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConsumingRestApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8082"));
		app.run(args);
		// SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public WebClient getWebClient() {
		return WebClient.create("http://localhost:8080");
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.setConnectTimeout(Duration.ofSeconds(5)).setReadTimeout(Duration.ofSeconds(5)).build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		// create person
		// return args -> {
		// Person person = new Person(null, "Peter", 18);
		// String result = restTemplate.postForObject (
		// "http://localhost:8080/greeting", person, String.class);
		// log.debug("result:{}", result);
		// };

		// // get person
//		 return args -> {
//		 Person person = restTemplate.getForObject(
//		 "http://localhost:8080/person/1", Person.class);
//		 log.info(person.toString());
//		 };
		//
		// // get all persons
		return args -> {
			log.info("i am here");
			List persons = restTemplate.getForObject(
					"http://localhost:8080/person", List.class);
			log.info(persons.toString());
		};
		//
		// // update person
		// return args -> {
		// Person person = new Person(Long.valueOf("1"), "Tom", 19);
		// restTemplate.put(
		// "http://localhost:8080/person", person, Person.class);
		// };
		//
		// // delete person
		// return args -> {
		// restTemplate.delete(
		// "http://localhost:8080/person/1");
		// };
	}
}
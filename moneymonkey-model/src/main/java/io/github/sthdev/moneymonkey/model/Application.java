package io.github.sthdev.moneymonkey.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableNeo4jRepositories
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {

			personRepository.deleteAll();

			Person p1 = Person.builder().firstName("Bibsi").build();
			Person p2 = Person.builder().firstName("Stjopchen").partner(p1).build();

			List<Person> team = Arrays.asList(p1, p2);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(p1);
			personRepository.save(p2);

			log.info("Lookup each person by name...");
			team.stream().forEach(
					person -> log.info("\t" + personRepository.findByFirstName(person.getFirstName()).toString()));
		};
	}
}

package io.github.sthdev.moneymonkey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import io.github.sthdev.moneymonkey.model.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableNeo4jRepositories
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner demo(AccountHolderRepository accountHolderRepository) {
		return args -> {
			accountHolderRepository.deleteAll();

			// AccountHolder accountHolder =
			// AccountHolder.builder().firstName("Max").lastName("Müller").build();
			//
			// log.info("Saving data in database...");
			//
			// accountHolderRepository.save(accountHolder);
			//
			// log.info("Saved data to database.");
		};
	}
}

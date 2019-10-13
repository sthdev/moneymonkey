package io.github.sthdev.moneymonkey;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import io.github.sthdev.moneymonkey.model.User;
import io.github.sthdev.moneymonkey.model.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableNeo4jRepositories
@Slf4j
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner demo(UserRepository userRepository) {
		return args -> {

			userRepository.deleteAll();

			User user1 = User.builder().username("Bibsi").build();

			log.info("Storing data...");

			userRepository.save(user1);
		};
	}
}

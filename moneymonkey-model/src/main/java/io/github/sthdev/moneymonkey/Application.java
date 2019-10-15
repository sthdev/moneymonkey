package io.github.sthdev.moneymonkey;

import java.time.LocalDateTime;

import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import io.github.sthdev.moneymonkey.model.CurrentAccount;
import io.github.sthdev.moneymonkey.model.User;
import io.github.sthdev.moneymonkey.model.repositories.CurrentAccountRepository;
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
	CommandLineRunner demo(UserRepository userRepository, CurrentAccountRepository currentAccountRepository) {
		return args -> {

			userRepository.deleteAll();
			currentAccountRepository.deleteAll();

			User user1 = User.builder().username("Bibsi").build();

			CurrentAccount account = CurrentAccount.builder().name("Spaßkasse Giro-Konto").iban("DE1234567890")
					.bic("ABCDEF").created(LocalDateTime.now())
					.initialBalance(Money.of(100000, Monetary.getCurrency("EUR"))).build();

			user1.getOwnedAccounts().add(account);

			log.info("Saving data in database...");

			userRepository.save(user1);
			currentAccountRepository.save(account);

			log.info("Saved data to database.");
		};
	}
}

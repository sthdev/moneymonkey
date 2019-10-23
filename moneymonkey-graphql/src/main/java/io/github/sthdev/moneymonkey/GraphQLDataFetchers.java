package io.github.sthdev.moneymonkey;

import org.neo4j.driver.internal.util.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import io.github.sthdev.moneymonkey.model.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GraphQLDataFetchers {

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	public DataFetcher<?> getAccountHolders() {
		return env -> {
			log.info("Retrieving all account holders...");
			return Iterables.asList(accountHolderRepository.findAll());
		};
	}
}

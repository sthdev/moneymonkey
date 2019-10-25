package io.github.sthdev.moneymonkey.graphql;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.neo4j.driver.internal.util.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.idl.TypeRuntimeWiring;
import io.github.sthdev.moneymonkey.model.AccountHolder;
import io.github.sthdev.moneymonkey.model.repositories.AccountHolderRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountHolderDataFetchers {

	private static final String QUERY = "Query";

	private static final String MUTATION = "Mutation";

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	public List<TypeRuntimeWiring> createTypeRuntimeWirings() {
		return Arrays.asList(
				TypeRuntimeWiring.newTypeWiring(QUERY).dataFetcher("getAccountHolder", getAccountHolder())
						.dataFetcher("getAccountHolders", getAccountHolders()).build(),
				TypeRuntimeWiring.newTypeWiring(MUTATION)
						// .dataFetcher("createAccountHolder", createAccountHolder())
						.dataFetcher("deleteAccountHolder", deleteAccountHolder()).build());
	}

	public DataFetcher<AccountHolder> getAccountHolder() {
		return env -> {
			String idAsString = requireNonNull(env.getArgument("id"));
			Long id = Long.valueOf(idAsString);

			Optional<AccountHolder> accountHolder = accountHolderRepository.findById(id);

			return accountHolder.orElseThrow(() -> new AccountHolderNotFoundException(id));
		};
	}

	public DataFetcher<List<AccountHolder>> getAccountHolders() {
		return env -> {
			log.info("Retrieving all account holders...");
			return Iterables.asList(accountHolderRepository.findAll());
		};
	}

	// public DataFetcher<AccountHolder> createAccountHolder() {
	// return env -> {
	// String firstName = requireNonNull(env.getArgument("firstName"));
	// String lastName = requireNonNull(env.getArgument("lastName"));
	//
	// AccountHolder accountHolder =
	// AccountHolder.builder().firstName(firstName).lastName(lastName).build();
	//
	// accountHolder = accountHolderRepository.save(accountHolder);
	//
	// log.info("Created account holder {}...", accountHolder);
	//
	// return accountHolder;
	// };
	// }

	// public DataFetcher<AccountHolder> createAccountHolder() {
	// return env -> {
	// Map<String, Object> accountHolderInput =
	// requireNonNull(env.getArgument("accountHolder"));
	//
	// accountHolder = accountHolderRepository.save(accountHolder);
	//
	// log.info("Created account holder {}...", accountHolder);
	//
	// return accountHolder;
	// };
	// }

	public DataFetcher<AccountHolder> deleteAccountHolder() {
		return env -> {
			String idAsString = requireNonNull(env.getArgument("id"));
			Long id = Long.valueOf(idAsString);

			Optional<AccountHolder> accountHolder = accountHolderRepository.findById(id);

			if (accountHolder.isPresent()) {
				accountHolderRepository.delete(accountHolder.get());

				log.info("Deleted account holder {}...", accountHolder);
			}

			return accountHolder.orElseThrow(() -> new AccountHolderNotFoundException(id));
		};
	}

}

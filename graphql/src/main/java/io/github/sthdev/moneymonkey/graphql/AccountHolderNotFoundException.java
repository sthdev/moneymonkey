package io.github.sthdev.moneymonkey.graphql;

import io.github.sthdev.moneymonkey.model.AccountHolder;

/**
 * Thrown if an {@link AccountHolder} could not be found in the database.
 */
public class AccountHolderNotFoundException extends RuntimeException {

	public AccountHolderNotFoundException(Long id) {
		super("AccountHolder with id '" + id + "' could not be found.");
	}

}

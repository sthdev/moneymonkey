package io.github.sthdev.moneymonkey.model;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	Person findByFirstName(String firstName);
}

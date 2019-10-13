package io.github.sthdev.moneymonkey.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public abstract class Entity {

	@Id
	@GeneratedValue
	private Long id;
}

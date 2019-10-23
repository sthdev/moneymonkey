package io.github.sthdev.moneymonkey.old.model;

import java.time.LocalDateTime;
import javax.money.MonetaryAmount;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import io.github.sthdev.moneymonkey.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NodeEntity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends Entity {

	private String title;

	private String comment;

	private MonetaryAmount amount;

	private LocalDateTime timestamp;

	private TransactionCategory category;

	/**
	 * If set, this and the opposite {@link Transaction} represent a transaction
	 * from one {@link Account} to another. Obviously, their amounts must be inverse
	 * to each other.
	 */
	@Relationship(direction = Relationship.UNDIRECTED)
	private Transaction opposite;

}

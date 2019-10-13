package io.github.sthdev.moneymonkey.model;

import java.util.List;

import javax.money.MonetaryAmount;

import org.neo4j.ogm.annotation.NodeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Account extends Entity {

	private String name;

	private MonetaryAmount initialBalance;

	private List<Transaction> transactions;
}
package io.github.sthdev.moneymonkey.model;

import java.time.LocalDateTime;
import java.util.List;

import org.javamoney.moneta.Money;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import io.github.sthdev.moneymonkey.converters.MoneyConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class Account extends Entity {

	private String name;

	@Convert(value = MoneyConverter.class)
	private Money initialBalance;

	private List<Transaction> transactions;

	private LocalDateTime created;
}

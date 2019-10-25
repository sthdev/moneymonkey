package io.github.sthdev.moneymonkey.old.model;

import java.util.Set;

import org.neo4j.ogm.annotation.NodeEntity;

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
public class TransactionCategory extends Entity {

	private String name;

	private Set<TransactionCategory> nestedCategories;
}

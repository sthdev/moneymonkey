package io.github.sthdev.moneymonkey.converters;

import org.javamoney.moneta.Money;
import org.neo4j.ogm.typeconversion.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, String> {

	@Override
	public String toGraphProperty(Money value) {
		return value.toString();
	}

	@Override
	public Money toEntityAttribute(String value) {
		return Money.parse(value);
	}

}

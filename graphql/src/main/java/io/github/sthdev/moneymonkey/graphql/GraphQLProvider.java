package io.github.sthdev.moneymonkey.graphql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.RuntimeWiring.Builder;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLProvider {

	@Autowired
	AccountHolderDataFetchers accountHolderDataFetchers;

	private GraphQL graphQL;

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

	@PostConstruct
	public void init() throws IOException {
		TypeDefinitionRegistry typeRegistry = null;

		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("money-monkey.graphqls")) {
			try (InputStreamReader reader = new InputStreamReader(inputStream)) {
				typeRegistry = new SchemaParser().parse(reader);

			}
		}

		RuntimeWiring runtimeWiring = buildWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);

		graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private RuntimeWiring buildWiring() {
		Builder wiring = RuntimeWiring.newRuntimeWiring();

		accountHolderDataFetchers.createTypeRuntimeWirings().forEach(typeWiring -> wiring.type(typeWiring));

		return wiring.build();
	}

}

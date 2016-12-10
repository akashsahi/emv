package com.akash.evm.config;

import com.akash.evm.model.Brand;
import com.akash.evm.model.Category;
import com.akash.evm.model.Documents;
import com.akash.evm.model.Feedback;
import com.akash.evm.model.User;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

public class JsonSchemaConverter {
	public static void main(String[] args) throws JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// configure mapper, if necessary, then create schema generator
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		JsonSchema schema = schemaGen.generateSchema(Brand.class);
		System.out.println("Brand>>" + mapper.generateJsonSchema(Brand.class));
		System.out.println("Category>>" + mapper.generateJsonSchema(Category.class));
		System.out.println("Documents>>" + mapper.generateJsonSchema(Documents.class));
		System.out.println("Feedback>>" + mapper.generateJsonSchema(Feedback.class));
		System.out.println("User>>" + mapper.generateJsonSchema(User.class));
	}
}

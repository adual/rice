package com.taodaye.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONObjectUtils {

	public static <T> T json2Ojbect(String jsonInString, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();

		T obj = mapper.readValue(jsonInString, clazz);
		return obj;
	}
}

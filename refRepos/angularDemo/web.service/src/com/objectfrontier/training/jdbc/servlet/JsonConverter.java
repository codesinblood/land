package com.objectfrontier.training.jdbc.servlet;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.objectfrontier.training.jdbc.exceptions.AppException;
import com.objectfrontier.training.jdbc.exceptions.ErrorCode;

public class JsonConverter {

    private static ObjectMapper mapper = null;
    private static ObjectMapper getObjectMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        }
        return mapper;
    }

    public static String toJson(Object o) {

        try {
            return getObjectMapper().writeValueAsString(o);
        } catch (Exception e) {
//            return (e.getMessage());
            throw new AppException(ErrorCode.SERVER, e);
//            throw new AppException(e);
        }
    }

    public static <T> T toObject(String jsonString, Class<? extends T> type) throws Exception {

        try {
            if (!("".equals(jsonString))) {
                return getObjectMapper().readValue(jsonString, type);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            throw new AppException(ErrorCode.SERVER, e);
//            return (e.getMessage());
        }
        return null;
    }

    public static <T> T toObject(Reader reader, Class<? extends T> type) throws Exception {

        List<String> jsonLines = ((BufferedReader)reader).lines().collect(Collectors.toList());
        String addressJson = String.join("", jsonLines);

        return toObject(addressJson, type);

    }

    public static <T> List<T> toList(String json, Class<T> elementType) {

        try {

            ObjectMapper mapper = getObjectMapper();

            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, elementType);

            return mapper.readValue(json, listType);

        } catch (Exception e) {
            throw new AppException(ErrorCode.SERVER, e.getCause());

        }
    }

}

package com.javarush.yashpatrov.quest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.yashpatrov.quest.model.QuestionContainer;

import java.io.InputStream;

public class QuestionContainerFactory {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static QuestionContainer createFromJson(String resourcePath) {
        QuestionContainer questionContainer = null;
        try {
            InputStream inputStream = QuestionContainerFactory.class.getClassLoader().getResourceAsStream(resourcePath);
            questionContainer = MAPPER.readValue(inputStream, QuestionContainer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionContainer;
    }
}

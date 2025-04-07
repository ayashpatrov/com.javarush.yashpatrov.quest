package com.javarush.yashpatrov.quest.model;

import lombok.Data;

import java.util.List;

@Data
public class QuestionContainer {
    List<Question> questions;
}

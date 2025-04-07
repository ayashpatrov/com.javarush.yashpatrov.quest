package com.javarush.yashpatrov.quest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private int id;
    private String text;
    private int nextQuestionId;
}

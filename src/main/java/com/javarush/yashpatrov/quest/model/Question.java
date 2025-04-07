package com.javarush.yashpatrov.quest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private String text;
    private int id;
    private List<Answer> answers = new ArrayList<>();
}

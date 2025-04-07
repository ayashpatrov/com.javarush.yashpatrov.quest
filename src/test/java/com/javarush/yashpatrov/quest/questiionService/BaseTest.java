package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.model.Answer;
import com.javarush.yashpatrov.quest.model.Question;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    public Question questionWithAnswers = new Question("Вопрос", 1,
            List.of(new Answer(1, "text", 2),
                    new Answer(2, "text", 3))
    );
    public Question questionWithDuplicateAnswers = new Question("Вопрос", 2,
            List.of(new Answer(3, "text", 2),
                    new Answer(3, "text", 5))
    );
    public Question duplicateQuestionWithSameAnswer = new Question("Вопрос", 3,
            List.of(new Answer(1, "text", 2),
                    new Answer(5, "text", 3))
    );
    public int answerDoesntExistId = -1;
    public Question questionWithoutAnswers = new Question("Вопрос", 4, new ArrayList<>());
}

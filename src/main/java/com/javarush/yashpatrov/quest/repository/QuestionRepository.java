package com.javarush.yashpatrov.quest.repository;

import com.javarush.yashpatrov.quest.model.Answer;
import com.javarush.yashpatrov.quest.model.Question;
import com.javarush.yashpatrov.quest.model.QuestionContainer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionRepository {
    protected QuestionContainer questionContainer;

    public QuestionRepository(QuestionContainer questionContainer) {
        this.questionContainer = questionContainer;
    }

    public Question findById(int id) {
        return questionContainer.getQuestions().stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Question findFirstQuestion() {
        return questionContainer.getQuestions().stream().findFirst().orElse(null);
    }

    public Question findNextQuestionByAnswerId(int answerId) {
        List<Question> question = questionContainer
                .getQuestions()
                .stream()
                .filter(q -> (q.getAnswers().stream().anyMatch(a -> a.getId() == answerId))).collect(Collectors.toList());
        if (question.isEmpty()) {
            return null;
        } else if (question.size() > 1) {
            throw new RuntimeException(String.format("В базе содержится более одного вопроса с одинаковыми ответами c id = %d", answerId));
        } else {
            List<Answer> answers = question.get(0).getAnswers().stream().filter(a -> a.getId() == answerId).collect(Collectors.toList());
            if (answers.isEmpty()) {
                return null;
            } else if (answers.size() > 1) {
                throw new RuntimeException(String.format("В базе дублируется вопрос с id = %d", answerId));
            } else {
                int nextQuestionId = answers.get(0).getNextQuestionId();
                return questionContainer.getQuestions().stream().filter(q -> q.getId() == nextQuestionId).findFirst().orElse(null);
            }
        }
    }

    public List<Answer> findAllAnswersByQuestionId(int questionId) {
        return Objects.requireNonNull(questionContainer.getQuestions().stream().filter(q -> q.getId() == questionId).findFirst().orElse(null)).getAnswers();
    }
}

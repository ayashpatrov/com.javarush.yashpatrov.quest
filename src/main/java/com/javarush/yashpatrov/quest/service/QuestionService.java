package com.javarush.yashpatrov.quest.service;

import com.javarush.yashpatrov.quest.model.Question;
import com.javarush.yashpatrov.quest.repository.QuestionRepository;

public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getNextQuestion(Integer answerId) {
        return questionRepository.findNextQuestionByAnswerId(answerId);
    }

    public Question getFirstQuestion() {
        return questionRepository.findFirstQuestion();
    }

    public Question getQuestionById(int questionId) {
        return questionRepository.findById(questionId);
    }
}

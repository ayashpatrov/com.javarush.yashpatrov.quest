package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.repository.QuestionRepository;
import com.javarush.yashpatrov.quest.service.QuestionService;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceTest extends BaseTest {
    private QuestionRepository questionRepository;
    private QuestionService questionService;

    @BeforeEach
    public void setUp() {
        questionRepository = mock(QuestionRepository.class);
        when(questionRepository.findFirstQuestion()).thenReturn(questionWithAnswers);
        when(questionRepository.findNextQuestionByAnswerId(1)).thenReturn(questionWithoutAnswers);
        questionService = new QuestionService(questionRepository);
    }
    @Test
    @Description("Получение первого вопроса")
    public void getFirstQuestionTest() {
        Assertions.assertThat(questionService.getFirstQuestion()).isEqualTo(questionWithAnswers);
    }
    @Test
    @Description("Получение следующего вопроса")
    public void getNextQuestionByAnswerIdTest() {
        Assertions.assertThat(questionService.getNextQuestion(existsAnswerId)).isEqualTo(questionWithoutAnswers);
    }

    @Test
    @Description("Получение следующего вопроса по несуществуюшему ответу")
    public void getNextQuestionIsNullTest() {
        Assertions.assertThat(questionService.getNextQuestion(answerDoesntExistId)).isNull();
    }
}

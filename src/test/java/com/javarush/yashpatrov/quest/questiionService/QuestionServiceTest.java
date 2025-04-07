package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.repository.QuestionRepository;
import com.javarush.yashpatrov.quest.service.QuestionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionServiceTest extends BaseTest {
    private QuestionRepository repositoryMock;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(QuestionRepository.class);
        when(repositoryMock.findFirstQuestion()).thenReturn(questionWithAnswers);
        when(repositoryMock.findById(1)).thenReturn(questionWithAnswers);
        when(repositoryMock.findById(2)).thenReturn(questionWithoutAnswers);
        when(repositoryMock.findNextQuestionByAnswerId(1)).thenReturn(questionWithoutAnswers);
        when(repositoryMock.findNextQuestionByAnswerId(2)).thenReturn(questionWithoutAnswers);
        when(repositoryMock.findNextQuestionByAnswerId(3)).thenThrow(IllegalArgumentException.class);
        questionService = new QuestionService(repositoryMock);
    }
    @Test
    public void getFirstQuestionTest() {
        Assertions.assertThat(questionService.getFirstQuestion()).isEqualTo(questionWithAnswers);
    }
    @Test
    public void getNextQuestionTest() {
        Assertions.assertThat(questionService.getNextQuestion(questionWithAnswers.getAnswers().get(0).getId())).isEqualTo(questionWithoutAnswers);
    }

    @Test
    public void getNextQuestionTest2() {
        Assertions.assertThat(questionService.getNextQuestion(answerDoesntExistId)).isNull();
    }
}

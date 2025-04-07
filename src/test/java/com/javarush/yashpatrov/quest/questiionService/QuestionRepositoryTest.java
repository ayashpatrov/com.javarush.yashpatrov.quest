package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.model.QuestionContainer;
import com.javarush.yashpatrov.quest.repository.QuestionRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class QuestionRepositoryTest extends BaseTest {
    static QuestionContainer questionContainerMock;
    QuestionRepository questionRepository;

    @BeforeEach
    public void init() {
        questionContainerMock = Mockito.mock(QuestionContainer.class);
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithAnswers,
                questionWithoutAnswers
                ));
        questionRepository = new QuestionRepository(questionContainerMock);
    }

    @Test
    public void findFirstTest() {
        Assertions.assertThat(questionRepository.findFirstQuestion()).isEqualTo(questionWithAnswers);
    }

    @Test
    public void findByIdFoundTest() {
        Assertions.assertThat(questionRepository.findById(1)).isEqualTo(questionWithAnswers);
    }

    @Test
    public void findByIdNotFoundTest() {
        Assertions.assertThat(questionRepository.findById(-1)).isNull();
    }

    @Test
    public void findNextQuestionByAnswerFoundTest() {
        Assertions.assertThat(questionRepository.findNextQuestionByAnswerId(1)).isEqualTo(questionWithoutAnswers);
    }

    @Test
    public void findNextQuestionByAnswerNotFoundTest() {
        Assertions.assertThat(questionRepository.findNextQuestionByAnswerId(-1)).isNull();
    }

    @Test
    public void findNextQuestionByAnswerDuplicatesTest() {
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithDuplicateAnswers
        ));
        questionRepository = new QuestionRepository(questionContainerMock);

        Exception exception = assertThrows(RuntimeException.class, () -> questionRepository.findNextQuestionByAnswerId(3));
        Assertions.assertThat(exception.getMessage()).isEqualTo("В базе дублируется вопрос с id = 3");
    }

    @Test
    public void findNextQuestionByAnswerDuplicateAnswersTest() {
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithAnswers,
                duplicateQuestionWithSameAnswer
        ));
        questionRepository = new QuestionRepository(questionContainerMock);

        Exception exception = assertThrows(RuntimeException.class, () -> questionRepository.findNextQuestionByAnswerId(1));
        Assertions.assertThat(exception.getMessage()).isEqualTo("В базе содержится более одного вопроса с одинаковыми ответами c id = 1");
    }
}

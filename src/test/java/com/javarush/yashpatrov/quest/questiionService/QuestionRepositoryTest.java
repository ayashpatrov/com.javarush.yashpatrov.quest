package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.model.QuestionContainer;
import com.javarush.yashpatrov.quest.repository.QuestionRepository;

import jdk.jfr.Description;
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
    public void setUp() {
        questionContainerMock = Mockito.mock(QuestionContainer.class);
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithAnswers,
                questionWithoutAnswers
                ));
        questionRepository = new QuestionRepository(questionContainerMock);
    }

    @Test
    @Description("Получение первого вопроса")
    public void findFirstTest() {
        Assertions.assertThat(questionRepository.findFirstQuestion()).isEqualTo(questionWithAnswers);
    }

    @Test
    @Description("Получение вопроса по Id, вопрос найден")
    public void findByIdFoundTest() {
        Assertions.assertThat(questionRepository.findById(existsAnswerId)).isEqualTo(questionWithAnswers);
    }

    @Test
    @Description("Получение вопроса по Id, вопрос не найден")
    public void findByIdNotFoundTest() {
        Assertions.assertThat(questionRepository.findById(answerDoesntExistId)).isNull();
    }

    @Test
    @Description("Получение вопроса по Id ответа, вопрос найден")
    public void findNextQuestionByAnswerFoundTest() {
        Assertions.assertThat(questionRepository.findNextQuestionByAnswerId(existsAnswerId)).isEqualTo(questionWithoutAnswers);
    }

    @Test
    @Description("Получение вопроса по Id ответа, вопрос не найден")
    public void findNextQuestionByAnswerNotFoundTest() {
        Assertions.assertThat(questionRepository.findNextQuestionByAnswerId(answerDoesntExistId)).isNull();
    }

    @Test
    @Description("Получение вопроса по Id ответа, неконсистентные данные, есть дубли вопроса.")
    public void findNextQuestionByAnswerDuplicatesTest() {
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithDuplicateAnswers
        ));
        questionRepository = new QuestionRepository(questionContainerMock);

        Exception exception = assertThrows(RuntimeException.class, () -> questionRepository.findNextQuestionByAnswerId(3));
        Assertions.assertThat(exception.getMessage()).isEqualTo("В базе дублируется вопрос с id = 3");
    }

    @Test
    @Description("Получение вопроса по Id ответа, неконсистентные данные, есть дубли отовета.")
    public void findNextQuestionByAnswerDuplicateAnswersTest() {
        when(questionContainerMock.getQuestions()).thenReturn(List.of(
                questionWithAnswers,
                duplicateQuestionWithSameAnswer
        ));
        questionRepository = new QuestionRepository(questionContainerMock);

        Exception exception = assertThrows(RuntimeException.class, () -> questionRepository.findNextQuestionByAnswerId(existsAnswerId));
        Assertions.assertThat(exception.getMessage()).isEqualTo("В базе содержится более одного вопроса с одинаковыми ответами c id = " + existsAnswerId);
    }
}

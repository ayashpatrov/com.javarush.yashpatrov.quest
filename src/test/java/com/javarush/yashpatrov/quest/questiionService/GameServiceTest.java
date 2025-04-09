package com.javarush.yashpatrov.quest.questiionService;

import com.javarush.yashpatrov.quest.model.Question;
import com.javarush.yashpatrov.quest.service.GameService;
import com.javarush.yashpatrov.quest.service.QuestionService;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameServiceTest extends BaseTest {
    private static GameService gameServiceMock;
    private static QuestionService questionServiceMock;
    private static HttpServletRequest reqMock;
    private static HttpServletResponse respMock;
    private static HttpSession sessionMock;

    @BeforeEach
    public void setUp() {
        questionServiceMock = mock(QuestionService.class);
        gameServiceMock = new GameService(questionServiceMock);
        reqMock = mock(HttpServletRequest.class);
        respMock = mock(HttpServletResponse.class);
        sessionMock = Mockito.mock(HttpSession.class);
    }

    @Test
    @Description("Обработка запроса, если answerId не передан")
    public void processAnswerNull() {
        when(reqMock.getParameter("answerId")).thenReturn(null);
        when(reqMock.getSession(true)).thenReturn(sessionMock);

        try {
            gameServiceMock.processAnswer(reqMock, respMock);
            Mockito.verify(respMock, description("Ожидался редирект на \"game\"")).sendRedirect("game");
            Mockito.verify(sessionMock, never().description("Установка атрибутов сессии не должна была вызываться"))
                    .setAttribute(anyString(), any());
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Description("Обработка запроса, если по ответу найден вопрос без ответов (Терминальный вопрос)")
    public void processAnswerExistAnswerWithoutQuestion() {
        when(reqMock.getParameter("answerId")).thenReturn(existsAnswerId + "");
        when(reqMock.getSession(true)).thenReturn(sessionMock);

        try {
            gameServiceMock.processAnswer(reqMock, respMock);
            Mockito.verify(respMock, description("Ожидался редирект на \"game\"")).sendRedirect("game");
            Mockito.verify(sessionMock, times(1).description("Ожидалась установка атрибута сессии \"CURRENT_QUESTION_ID\" = null"))
                    .setAttribute("CURRENT_QUESTION_ID", null);

        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Description("Обработка запроса, если по ответу найден вопрос с ответами")
    public void processAnswerExistAnswerWithNextQuestion() {
        when(questionServiceMock.getNextQuestion(existsAnswerId)).thenReturn(questionWithAnswers);
        when(reqMock.getParameter("answerId")).thenReturn(existsAnswerId + "");
        when(reqMock.getSession(true)).thenReturn(sessionMock);

        try {
            gameServiceMock.processAnswer(reqMock, respMock);
            Mockito.verify(respMock, description("Ожидался редирект на \"game\"")).sendRedirect("game");
            Mockito.verify(sessionMock, times(1).description("Ожидалась установка атрибута сессии \"CURRENT_QUESTION_ID\" = " + questionWithAnswers.getId()))
                    .setAttribute("CURRENT_QUESTION_ID", questionWithAnswers.getId());

        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Description("Получение следующего вопроса, если сессия ещё пуста")
    public void getNextQuestionEmptySession() {
        when(reqMock.getSession(true)).thenReturn(null);
        when(reqMock.getParameter("answerId")).thenReturn(existsAnswerId + "");
        when(reqMock.getSession(true)).thenReturn(sessionMock);
        when(questionServiceMock.getFirstQuestion()).thenReturn(questionWithAnswers);

        Question nextQuestion = gameServiceMock.getCurrentQuestion(reqMock);
        Assertions.assertThat(nextQuestion).isEqualTo(questionWithAnswers);
        Mockito.verify(sessionMock, times(1).description("Ожидалась установка атрибута сессии \"CURRENT_QUESTION_ID\" = " + questionWithAnswers.getId()))
                .setAttribute("CURRENT_QUESTION_ID", questionWithAnswers.getId());;
    }

    @Test
    @Description("Получение следующего вопроса, если он есть в сессии")
    public void getNextQuestionSessionHasQuestion() {
        when(reqMock.getSession(true)).thenReturn(null);
        when(reqMock.getParameter("answerId")).thenReturn("1");
        when(reqMock.getSession(true)).thenReturn(sessionMock);
        when(sessionMock.getAttribute("CURRENT_QUESTION_ID")).thenReturn(questionWithoutAnswers.getId());
        when(questionServiceMock.getFirstQuestion()).thenReturn(questionWithAnswers);
        when(questionServiceMock.getQuestionById(questionWithoutAnswers.getId())).thenReturn(questionWithoutAnswers);

        Question nextQuestion = gameServiceMock.getCurrentQuestion(reqMock);
        Assertions.assertThat(nextQuestion).isEqualTo(questionWithoutAnswers);
    }
}

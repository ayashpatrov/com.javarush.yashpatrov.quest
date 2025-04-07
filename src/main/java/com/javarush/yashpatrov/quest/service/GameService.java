package com.javarush.yashpatrov.quest.service;

import com.javarush.yashpatrov.quest.model.Question;
import com.javarush.yashpatrov.quest.model.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GameService {
    QuestionService questionService;
    public GameService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public void processAnswer(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            HttpSession currentSession = req.getSession(true);
            String answerId = req.getParameter("answerId");
            if (answerId != null) {
                try {
                    Question question = questionService.getNextQuestion(Integer.parseInt(answerId));
                    if (question != null) {
                        currentSession.setAttribute(SessionAttributes.CURRENT_QUESTION_ID.name(), question.getId());
                    } else {
                        currentSession.setAttribute(SessionAttributes.CURRENT_QUESTION_ID.name(), null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            resp.sendRedirect("game");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    public Question getCurrentQuestion(HttpServletRequest req) {
        HttpSession currentSession = req.getSession(true);
        Integer currentQuestionId = (Integer) currentSession.getAttribute(SessionAttributes.CURRENT_QUESTION_ID.name());
        Question question;
        if (currentQuestionId == null) {
            question = questionService.getFirstQuestion();
            currentSession.setAttribute(SessionAttributes.CURRENT_QUESTION_ID.name(), question.getId());
        } else {
            question = questionService.getQuestionById(currentQuestionId);
        }
        return question;
    }
}

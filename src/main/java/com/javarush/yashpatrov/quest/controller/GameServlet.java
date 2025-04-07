package com.javarush.yashpatrov.quest.controller;

import com.javarush.yashpatrov.quest.model.Question;
import com.javarush.yashpatrov.quest.repository.QuestionRepository;
import com.javarush.yashpatrov.quest.service.GameService;
import com.javarush.yashpatrov.quest.service.QuestionContainerFactory;
import com.javarush.yashpatrov.quest.service.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    QuestionRepository repo = new QuestionRepository(QuestionContainerFactory.createFromJson("data.json"));
    QuestionService questionService = new QuestionService(repo);
    GameService gameService = new GameService(questionService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Question question = gameService.getCurrentQuestion(req);

            if (question != null) {
                req.setAttribute("question", question);
                getServletContext().getRequestDispatcher("/game.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        gameService.processAnswer(req, resp);
    }
}

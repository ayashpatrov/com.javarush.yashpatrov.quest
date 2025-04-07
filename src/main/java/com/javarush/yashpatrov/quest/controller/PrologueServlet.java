package com.javarush.yashpatrov.quest.controller;

import com.javarush.yashpatrov.quest.model.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PrologueServlet", value = "/prologue")
public class PrologueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute(SessionAttributes.CURRENT_QUESTION_ID.name(), null);
        Integer gamesPlayed = (Integer) session.getAttribute(SessionAttributes.GAMES_PLAYED.name());
        if (gamesPlayed != null) {
            session.setAttribute(SessionAttributes.GAMES_PLAYED.name(), ++gamesPlayed);
        } else {
            session.setAttribute(SessionAttributes.GAMES_PLAYED.name(), 0);
        }

        resp.sendRedirect("/prologue.jsp");
    }
}

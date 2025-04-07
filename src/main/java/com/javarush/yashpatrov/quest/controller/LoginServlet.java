package com.javarush.yashpatrov.quest.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.javarush.yashpatrov.quest.model.SessionAttributes.GAMES_PLAYED;
import static com.javarush.yashpatrov.quest.model.SessionAttributes.PLAYER_NAME;

@WebServlet(name = "LoginServlet", value = "/")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession currentSession = req.getSession(true);
        currentSession.invalidate();
        resp.sendRedirect("/login.jsp");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("playerName");
        HttpSession currentSession = req.getSession(true);
        currentSession.setAttribute(PLAYER_NAME.name(), name);

        resp.sendRedirect("/prologue");
    }
}
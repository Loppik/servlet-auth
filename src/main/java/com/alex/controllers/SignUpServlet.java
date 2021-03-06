package com.alex.controllers;

import com.alex.model.AuthService;
import com.alex.model.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject reqJson = JsonObject.getJsonObject(req);

        String email = reqJson.getString("email");
        String password = reqJson.getString("password");

        String result = new AuthService().singUp(email, password);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JSONObject respJson = new JSONObject();
        respJson.append("msg", result);
        out.print(respJson);
        out.flush();
    }
}
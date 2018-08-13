package org.apache.skywalking.testcase.undertow.controller;

import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright @ 2018/8/13
 *
 * @author cloudgc
 */
@WebServlet(name = "case", urlPatterns = "/case/undertow")
public class CaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("name"));
        resp.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8");
        resp.getWriter().write("success");
        resp.getWriter().close();
    }
}

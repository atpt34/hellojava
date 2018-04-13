package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;

public class ChangeLanguage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String temp = request.getParameter("lan");
        request.getSession().setAttribute("lang", temp);
        System.out.println("lan = " + temp);
        return "/index.jsp";
    }

}

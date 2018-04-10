package com.oleksii.controller.command.impl;
import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "redirect:/index.jsp";
    }
}

package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;

public class Exception implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        throw new RuntimeException("Generated exception");
    }
}
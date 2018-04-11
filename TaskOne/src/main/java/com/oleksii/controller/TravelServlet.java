package com.oleksii.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oleksii.controller.command.Command;
import com.oleksii.controller.command.impl.DefaultCommand;

import com.oleksii.controller.command.impl.Exception;
import com.oleksii.controller.command.impl.LogOut;
import com.oleksii.controller.command.impl.Login;
import com.oleksii.controller.command.impl.Ordering;
import com.oleksii.controller.command.impl.ProcessOrder;
import com.oleksii.controller.command.impl.Registration;
import com.oleksii.controller.command.impl.Travels;
import com.oleksii.model.service.impl.TravelServiceImpl;

public class TravelServlet extends HttpServlet {
    
    private final Map<String, Command> commands;
    
    public TravelServlet() {
        commands = new HashMap<>();
    }

    public void init(){
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("exception" , new Exception());
        commands.put("travels" , new Travels(new TravelServiceImpl()));
        commands.put("order" , new Ordering());
        commands.put("processOrder" , new ProcessOrder());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        response.setContentType("text/html");
        
        System.out.println(path);
        path = path.replaceAll(".*/app/" , "");
        System.out.println(path);
        Command command = commands.getOrDefault(path , new DefaultCommand());
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);

    }
}

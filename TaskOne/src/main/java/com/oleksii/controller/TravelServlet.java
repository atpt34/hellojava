package com.oleksii.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oleksii.controller.command.Command;
import com.oleksii.controller.command.impl.ChangeLanguage;
import com.oleksii.controller.command.impl.DefaultCommand;

import com.oleksii.controller.command.impl.Exception;
import com.oleksii.controller.command.impl.LogOut;
import com.oleksii.controller.command.impl.Login;
import com.oleksii.controller.command.impl.Ordering;
import com.oleksii.controller.command.impl.ProcessOrder;
import com.oleksii.controller.command.impl.ProcessTravelCreate;
import com.oleksii.controller.command.impl.ProcessTravelEdit;
import com.oleksii.controller.command.impl.ProcessTravelUpdate;
import com.oleksii.controller.command.impl.Registration;
import com.oleksii.controller.command.impl.Travels;
import com.oleksii.controller.command.impl.TravelsCreate;
import com.oleksii.controller.command.impl.TravelsDelete;
import com.oleksii.controller.command.impl.TravelsEdit;
import com.oleksii.controller.command.impl.TravelsSelectByType;
import com.oleksii.controller.command.impl.TravelsSort;

public class TravelServlet extends HttpServlet {

    
    private static final String COMMAND_PROCESS_ORDER = "processOrder";
    private static final String COMMAND_PROCESS_TRAVEL_EDIT = "processEdit";
    private static final String COMMAND_PROCESS_TRAVEL_UPDATE = "processUpdate";
    private static final String COMMAND_PROCESS_TRAVEL_CREATE = "processCreate";
    private static final String COMMAND_ORDER = "order";
    private static final String COMMAND_TRAVELS = "travels";
    private static final String COMMAND_EXCEPTION = "exception";
    private static final String COMMAND_REGISTRATION = "registration";
    private static final String COMMAND_LOGIN = "login";
    private static final String COMMAND_LOGOUT = "logout";
    private static final String COMMAND_CHANGE_LANGUAGE = "changeLanguage";
    private static final String COMMAND_TRAVELS_SORT = "travelsSort";
    private static final String COMMAND_TRAVELS_DELETE = "travelsDelete";
    private static final String COMMAND_TRAVELS_EDIT = "travelsEdit";
    private static final String COMMAND_TRAVELS_CREATE = "travelsCreate";
    private static final String COMMAND_TRAVELS_SELECT_BY_TYPE = "travelsSelectByType";
    
    private static final Command COMMAND_DEFAULT = new DefaultCommand();
    
    private final Map<String, Command> commands;
    

    public TravelServlet() {
        commands = new HashMap<>();
    }

    public void init() {
        commands.put(COMMAND_LOGOUT, new LogOut());
        commands.put(COMMAND_LOGIN, new Login());
        commands.put(COMMAND_REGISTRATION, new Registration());
        commands.put(COMMAND_EXCEPTION, new Exception());
        commands.put(COMMAND_CHANGE_LANGUAGE, new ChangeLanguage());
        commands.put(COMMAND_TRAVELS, new Travels());
        commands.put(COMMAND_TRAVELS_SORT, new TravelsSort());
        commands.put(COMMAND_TRAVELS_DELETE, new TravelsDelete());
        commands.put(COMMAND_TRAVELS_EDIT, new TravelsEdit());
        commands.put(COMMAND_TRAVELS_CREATE, new TravelsCreate());
        commands.put(COMMAND_TRAVELS_SELECT_BY_TYPE, new TravelsSelectByType());
        commands.put(COMMAND_ORDER, new Ordering());
        commands.put(COMMAND_PROCESS_ORDER, new ProcessOrder());
        commands.put(COMMAND_PROCESS_TRAVEL_EDIT, new ProcessTravelEdit());
        commands.put(COMMAND_PROCESS_TRAVEL_UPDATE, new ProcessTravelUpdate());
        commands.put(COMMAND_PROCESS_TRAVEL_CREATE, new ProcessTravelCreate());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        System.out.println(path);
        path = path.replaceAll(".*/travels/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path, COMMAND_DEFAULT);
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);

    }
}

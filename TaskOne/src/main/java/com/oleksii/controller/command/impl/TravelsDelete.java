package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;
import com.oleksii.model.service.ServiceFactory;
import com.oleksii.model.service.TravelService;

public class TravelsDelete implements Command {
    
    private final TravelService travelService = ServiceFactory.getTravelService();

    @Override
    public String execute(HttpServletRequest request) {

        travelService.deleteTravelById(Integer.parseInt(request.getParameter("id")));
        return "/index.jsp";
    }

}

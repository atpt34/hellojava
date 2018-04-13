package com.oleksii.controller.command.impl;


import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.service.ServiceFactory;
import com.oleksii.model.service.TravelService;

public class ProcessTravelEdit implements Command {
    
    private final TravelService travelService = ServiceFactory.getTravelService();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        TravelType type = TravelType.convertFromString(request.getParameter("type"));
        String transport = request.getParameter("transport");
        int cost = Integer.parseInt(request.getParameter("cost"));
        LocalDate start = LocalDate.parse(request.getParameter("start"));
        Travel t = new Travel(id, name, type, transport, cost, start);
        travelService.editTravel(t);
        return "/index.jsp";
    }

}

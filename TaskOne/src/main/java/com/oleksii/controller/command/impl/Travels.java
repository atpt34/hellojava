package com.oleksii.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;



import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.service.TravelService;


public class Travels implements Command {

    private final TravelService travelService;
    
    public Travels(TravelService travelService) {
        this.travelService = travelService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        List<Travel> t;
        switch (action) {
        case "sort":
            t = travelService.sortAllByCost();
            break;
        case "type":
            t = travelService.findByTravelType(TravelType.convertFromString(request.getParameter("param")));
            break;
        default:
            t = travelService.findAll();
            break;
        }
        request.setAttribute("travels", t);
        request.setAttribute("types", TravelType.values());
        return "/travels.jsp";
    }

}

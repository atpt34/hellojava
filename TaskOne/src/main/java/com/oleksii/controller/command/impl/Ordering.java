package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;

import com.oleksii.model.entity.Travel;
import com.oleksii.model.service.ServiceFactory;
import com.oleksii.model.service.TravelService;

public class Ordering implements Command {

    private final TravelService travelService = ServiceFactory.getTravelService();

    @Override
    public String execute(HttpServletRequest request) {
        String travelId = request.getParameter("travelId");
        int id = Integer.parseInt(travelId);
        if (!travelService.existsTravelById(id)) {
            throw new RuntimeException("No such travel in database");
        }
        Travel travel = travelService.findTravelById(id);
        System.out.println("travel " + travel);
        request.setAttribute("elem", travel);
        return "/order.jsp";
    }

}

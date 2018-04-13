package com.oleksii.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.service.ServiceFactory;
import com.oleksii.model.service.TravelService;

public class TravelsSelectByType implements Command {
    
    private final TravelService travelService = ServiceFactory.getTravelService();

    @Override
    public String execute(HttpServletRequest request) {
        List<Travel> t = travelService.findByTravelType(TravelType.convertFromString(request.getParameter("param")));
        request.setAttribute("travels", t);
        request.setAttribute("types", TravelType.values());
        return "/travels.jsp";
    }

}

package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;
import com.oleksii.model.entity.Order;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.service.OrderService;
import com.oleksii.model.service.ServiceFactory;
import com.oleksii.model.service.TravelService;


public class ProcessOrder implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();
    private final TravelService travelService = ServiceFactory.getTravelService();

    @Override
    public String execute(HttpServletRequest request) {

        String travelId = request.getParameter("travelId");
        int id = Integer.parseInt(travelId);
        Travel travel = travelService.findTravelById(id);
        String username = request.getParameter("name");
        String userphone = request.getParameter("phone");
        int numOfDays = Integer.parseInt(request.getParameter("days"));
        int numOfPeople = Integer.parseInt(request.getParameter("quantity"));
        Order order = new Order(-1, travel, username, userphone, numOfDays, numOfPeople);
        boolean makeOrder = orderService.makeOrder(order);
        System.out.println("makeOrder = " + makeOrder);
        return "/success.jsp";
    }

}

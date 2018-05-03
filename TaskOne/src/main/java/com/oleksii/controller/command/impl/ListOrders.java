package com.oleksii.controller.command.impl;

import javax.servlet.http.HttpServletRequest;

import com.oleksii.controller.command.Command;
import com.oleksii.model.service.OrderService;
import com.oleksii.model.service.ServiceFactory;

public class ListOrders implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();
    
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("orders", orderService.findAll());
        return "/orders.jsp";
    }

}

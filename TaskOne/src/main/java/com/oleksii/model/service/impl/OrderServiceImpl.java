package com.oleksii.model.service.impl;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.dao.impl.OrderDaoImpl;
import com.oleksii.model.entity.Order;
import com.oleksii.model.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final CrudDAO<Order, Integer> crud;
    {
        crud = new OrderDaoImpl();
    }
    
    @Override
    public boolean makeOrder(Order order) {
        return crud.save(order) != null;
    }

}

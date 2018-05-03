package com.oleksii.model.service.impl;

import java.util.List;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.dao.DaoFactory;
import com.oleksii.model.entity.Order;
import com.oleksii.model.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final CrudDAO<Order, Integer> crud = DaoFactory.getInstance().getOrderDao();

    @Override
    public boolean makeOrder(Order order) {
        crud.create(order);
        return true;
    }

    @Override
    public List<Order> findAll() {
        return crud.findAll();
    }

}

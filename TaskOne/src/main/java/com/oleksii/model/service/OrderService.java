package com.oleksii.model.service;

import java.util.List;

import com.oleksii.model.entity.Order;

public interface OrderService {

    boolean makeOrder(Order order);
    
    List<Order> findAll();
}

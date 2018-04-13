package com.oleksii.model.service;

import com.oleksii.model.service.impl.OrderServiceImpl;
import com.oleksii.model.service.impl.TravelServiceImpl;

public interface ServiceFactory {

    static TravelService getTravelService() {
        return new TravelServiceImpl();
    }
    static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

}

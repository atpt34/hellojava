package com.oleksii.model.dao;

import com.oleksii.model.dao.impl.OrderDaoImpl;
import com.oleksii.model.dao.impl.TravelDaoImpl;
import com.oleksii.model.entity.Order;
import com.oleksii.model.entity.Travel;

public class JdbcDaoFactory extends DaoFactory {
    
    private static CrudDAO<Travel, Integer> td;
    private static CrudDAO<Order, Integer> od;

    @Override
    public CrudDAO<Travel, Integer> getTravelDao() {        
        if (td == null) {
            synchronized (TravelDaoImpl.class) {
                if (td == null) {
                    td = new TravelDaoImpl();
                }
            }
        }
        return td;
    }

    @Override
    public CrudDAO<Order, Integer> getOrderDao() {        
        if (od == null) {
            synchronized (OrderDaoImpl.class) {
                if (od == null) {
                    od = new OrderDaoImpl();
                }
            }
        }
        return od;
    }

}

package com.oleksii.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.entity.Order;
import com.oleksii.model.util.DBConnection;

public class OrderDaoImpl implements CrudDAO<Order, Integer> {

    private static final String INSERT_INTO_ORDER = "INSERT INTO ordering "
            + "(username, userphone, travelId, days, quantity) VALUES( ?, ?, ?, ?, ?)";

    @Override
    public void create(Order o) {
        try {

            Object[] params = { o.getUsername(), o.getUserphone(), o.getTravel().getId(), o.getNumOfDays(),
                    o.getNumOfPeople() };
            ResultSet rs = DBConnection.makeUpdate(INSERT_INTO_ORDER, params);

            if (rs != null && rs.next()) {
                int key = rs.getInt(1);
//                System.out.println("key = " + key);
                o.setId(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findOne(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean exists(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Order t) {
        // TODO Auto-generated method stub
        
    }

}

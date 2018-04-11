package com.oleksii.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.entity.Order;
import com.oleksii.model.util.DBConnection;

public class OrderDaoImpl implements CrudDAO<Order, Integer> {

    private static final String INSERT_INTO_ORDER_SQL = "INSERT INTO ordering "
            + "(username, userphone, travelId, days, quantity)" + 
            "VALUES( ?, ?, ?, ?, ?)";
    
    @Override
    public Order save(Order o) {
        try {
//            Connection conn = DBConnection.getConnection();
//            PreparedStatement prepareStatement = conn.prepareStatement(INSERT_INTO_ORDER_SQL,
//                    Statement.RETURN_GENERATED_KEYS);
//            prepareStatement.setString(1, o.getUsername());
//            prepareStatement.setString(2, o.getUserphone());
//            prepareStatement.setInt(3, o.getTravel().getId());
//            prepareStatement.setInt(4, o.getNumOfDays());
//            prepareStatement.setInt(5, o.getNumOfPeople());
//            prepareStatement.executeUpdate();
//            ResultSet rs = prepareStatement.getGeneratedKeys();
            
            Object[] params = {o.getUsername(), o.getUserphone(), 
                    o.getTravel().getId(), o.getNumOfDays(), o.getNumOfPeople()};
            ResultSet rs = DBConnection.makeUpdate(INSERT_INTO_ORDER_SQL, params);
            
            
            if (rs != null && rs.next()) {
                int key = rs.getInt(1);
                System.out.println("key = " + key);
                o.setId(key);
            }
//            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(o);
        return o;
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

}

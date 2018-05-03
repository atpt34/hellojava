package com.oleksii.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.entity.Order;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.util.DBConnection;

public class OrderDaoImpl implements CrudDAO<Order, Integer> {

    private static final String INSERT_INTO_ORDER = "INSERT INTO ordering "
            + "(username, userphone, travelId, days, quantity) VALUES( ?, ?, ?, ?, ?)";
    
    private static final String SELECT_ALL = "SELECT ordering.id AS oid, username, userphone, "
            + "travelid, days, quantity, name, type, transport, cost, \"startDate\" FROM ordering " + 
            "INNER JOIN travel ON travel.id = ordering.travelid";

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

    /*@Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        Map<Integer, Travel> map = new HashMap<>();// eliminate duplicated Travel objects
        try(ResultSet resultSet = DBConnection.makeSelect(SELECT_ALL, new Object[]{})) {
//            ResultSet resultSet = DBConnection.makeSelect(SELECT_ALL, new Object[]{});
            while (resultSet.next()) {
                Order o = mapToOrder(resultSet);
                map.putIfAbsent(o.getTravel().getId(), o.getTravel());
                o.setTravel(map.get(o.getTravel().getId()));
                result.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }*/
    
    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        try (Connection conn = DBConnection.getDataSource().getConnection();
                PreparedStatement prepareStatement = conn.prepareStatement(SELECT_ALL);
                ResultSet rs = prepareAndExecute(conn, prepareStatement);) {
            int transactionIsolation = conn.getTransactionIsolation();
            System.out.println("transactionIsolation = " + transactionIsolation);
            System.out.println("Connection.TRANSACTION_NONE = " + Connection.TRANSACTION_NONE);
            System.out.println("Connection.TRANSACTION_READ_COMMITTED = " +  Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("Connection.TRANSACTION_READ_UNCOMMITTED = " + Connection.TRANSACTION_READ_UNCOMMITTED);
            System.out.println("Connection.TRANSACTION_REPEATABLE_READ = " + Connection.TRANSACTION_REPEATABLE_READ);
            System.out.println("Connection.TRANSACTION_SERIALIZABLE = " + Connection.TRANSACTION_SERIALIZABLE);
            conn.commit();
            Map<Integer, Travel> map = new HashMap<>();
            while (rs.next()) {
                Order o = mapToOrder(rs);
                Travel uniqueTravel = makeUniqueTravel(map, o);
                o.setTravel(uniqueTravel);
                result.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("conn closed");
        return result;
    }

    private Travel makeUniqueTravel(Map<Integer, Travel> map, Order o) {
        map.putIfAbsent(o.getTravel().getId(), o.getTravel());
        return map.getOrDefault(o.getTravel().getId(), o.getTravel());
    }

    private ResultSet prepareAndExecute(Connection conn, PreparedStatement prepareStatement) throws SQLException {
        conn.setAutoCommit(false);
        return prepareStatement.executeQuery();
    }

    private Order mapToOrder(ResultSet resultSet) throws SQLException {
        int oid = resultSet.getInt("oid");
        int travelid = resultSet.getInt("travelid");
        String name = resultSet.getString("name");
        TravelType type = TravelType.convertFromString(resultSet.getString("type"));
        String transport = resultSet.getString("transport");
        int cost = resultSet.getInt("cost");
        LocalDate start = resultSet.getDate("startDate").toLocalDate();
        Travel travel = new Travel(travelid, name, type, transport, cost, start);
        String username = resultSet.getString("username");
        String userphone = resultSet.getString("userphone");
        int numOfDays = resultSet.getInt("days");
        int numOfPeople = resultSet.getInt("quantity");
        return new Order(oid, travel, username, userphone, numOfDays, numOfPeople);
    }

    @Override
    public Order findOne(Integer id) {
        Order order = null;
        try (Connection conn = DBConnection.getDataSource().getConnection();
                ) {
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return order;
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
    public void update(Order t) {
        // TODO Auto-generated method stub
        
    }

    

}

package com.oleksii.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.util.DBConnection;

public class TravelDaoImpl implements CrudDAO<Travel, Integer> {

    private static final String TRAVEL_START_DATE = "startDate";
    private static final String TRAVEL_COST = "cost";
    private static final String TRAVEL_TRANSPORT = "transport";
    private static final String TRAVEL_TYPE = "type";
    private static final String TRAVEL_NAME = "name";
    private static final String TRAVEL_ID = "id";
    
    private static final String SELECT_FROM_TRAVEL = "SELECT * FROM travel";
    private static final String SELECT_FROM_TRAVEL_BY_ID = "SELECT * FROM travel WHERE id = ?";
    private static final String SELECT_COUNT_FROM_TRAVEL_BY_ID = "SELECT COUNT(*) FROM travel WHERE id = ?";
    private static final String DELETE_FROM_TRAVEL_BY_ID = "DELETE FROM travel WHERE id = ?";
    private static final String UPDATE_TRAVEL = "UPDATE travel SET name = ?, type = ?, transport = ?, cost = ?, \"startDate\" = ? WHERE id = ?";
    private static final String INSERT_TRAVEL = "INSERT INTO travel (name, type, transport, cost, \"startDate\") VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Travel> findAll() {
        List<Travel> result = new ArrayList<>();        
        try {
            ResultSet rs = DBConnection.makeSelect(SELECT_FROM_TRAVEL, new Object[] {});
            while (rs.next()) {
                Travel t = mapTravel(rs);
                result.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return result;
    }


    @Override
    public Travel findOne(Integer id) {
        try {
            ResultSet rs = DBConnection.makeSelect(SELECT_FROM_TRAVEL_BY_ID, new Object[] { id });
            if (rs.next()) {
                return mapTravel(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        try {
            DBConnection.makeUpdate(DELETE_FROM_TRAVEL_BY_ID, new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean exists(Integer id) {
        try {
            ResultSet rs = DBConnection.makeSelect(SELECT_COUNT_FROM_TRAVEL_BY_ID, new Object[] { id });
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Travel t) {
        try {
            DBConnection.makeUpdate(UPDATE_TRAVEL, 
                    new Object[]{t.getName(), t.getType().toString(), t.getTransport(), t.getCost(), t.getStart(), t.getId()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void create(Travel t) {
        try {
            DBConnection.makeUpdate(INSERT_TRAVEL, 
                    new Object[]{t.getName(), t.getType().toString(), t.getTransport(), t.getCost(), t.getStart()});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Travel mapTravel(ResultSet rs) throws SQLException {
        int id = rs.getInt(TRAVEL_ID);
        System.out.println(id);
        String name = rs.getString(TRAVEL_NAME);
        System.out.println(name);
        String type = rs.getString(TRAVEL_TYPE);
        System.out.println(type);
        String trans = rs.getString(TRAVEL_TRANSPORT);
        System.out.println(trans);
        int cost = rs.getInt(TRAVEL_COST);
        System.out.println(cost);
        LocalDate startDate = rs.getDate(TRAVEL_START_DATE).toLocalDate();
        System.out.println(startDate);
        return new Travel(id, name, TravelType.convertFromString(type), trans, cost, startDate);
    }
}

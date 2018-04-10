package com.oleksii.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.oleksii.model.dao.CrudDAO;
import com.oleksii.model.entity.Travel;
import com.oleksii.model.entity.TravelType;
import com.oleksii.model.util.DBConnection;

public class TravelDaoImpl implements CrudDAO<Travel, Integer> {

//    private DataSource dataSource;
//    
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }


    
    private static final String SELECT_FROM_TRAVEL_SQL = "SELECT * FROM travel";
    private static final String SELECT_FROM_TRAVEL_BY_ID_SQL = "SELECT * FROM travel WHERE id = ?";

    @Override
    public List<Travel> findAll() {
        List<Travel> result = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs;
            rs = statement.executeQuery(SELECT_FROM_TRAVEL_SQL);
//            ResultSetMetaData metaData = rs.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.println(metaData.getColumnType(i)); // java.sql.Types
//                System.out.println(metaData.getColumnLabel(i));
//                System.out.println(metaData.getColumnName(i));
//                System.out.println(metaData.getColumnClassName(i));
//            }
            while( rs.next()){
                int id = rs.getInt("id");
                System.out.println(id);
                String name = rs.getString("name");
                System.out.println(name);
                String type = rs.getString("type");
                System.out.println(type);
                String trans = rs.getString("transport");
                System.out.println(trans);
                int cost = rs.getInt("cost");
                System.out.println(cost);
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                System.out.println(startDate);
                result.add(new Travel(id, name, TravelType.convertFromString(type), trans, cost, startDate));
            }
//            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Travel findOne(Integer id) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement prepareStatement = con.prepareStatement(SELECT_FROM_TRAVEL_BY_ID_SQL);
            prepareStatement.setInt(1, id);
            ResultSet rs;
            rs = prepareStatement.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
                String type = rs.getString("type");
                System.out.println(type);
                String trans = rs.getString("transport");
                System.out.println(trans);
                int cost = rs.getInt("cost");
                System.out.println(cost);
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                System.out.println(startDate);
                return new Travel(id, name, TravelType.convertFromString(type), trans, cost, startDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public Travel save(Travel t) {
        // TODO Auto-generated method stub
        return null;
    }

}

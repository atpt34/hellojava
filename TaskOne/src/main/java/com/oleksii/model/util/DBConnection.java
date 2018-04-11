package com.oleksii.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

//@NonThreadSafe
public class DBConnection {

    private static final String SQLDB_DRIVER = "org.postgresql.Driver";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/travel";
    private static final String CONNECTION_USER = "lexa";
    private static final String CONNECTION_PASS= "lexa";
    private static final Connection CONNECTION;
    private static final Object LOCK;
    
    static {
        CONNECTION = Objects.requireNonNull(initConnection());
        LOCK = new Object();
    }
    
    private DBConnection() {}

    private static Connection initConnection() {
        Connection conn = null;
        try {
            Class.forName(SQLDB_DRIVER);
            conn = DriverManager.
                    getConnection(  CONNECTION_URL ,
                                    CONNECTION_USER ,
                                    CONNECTION_PASS);
            System.out.println("conn = " + conn);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    private static Connection getConnection() {
        return CONNECTION;
    }
    
    public static ResultSet makeUpdate(String sql, Object[] params) throws SQLException {
        checkQueryParams(sql, params);
        synchronized (LOCK) {
            PreparedStatement ps = CONNECTION.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setPreparedStatementParams(params, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            CONNECTION.commit();
            return rs;
        }
    }
    
    public static ResultSet makeSelect(String sql, Object[] params) throws SQLException {
        checkQueryParams(sql, params);
        synchronized (LOCK) {
            PreparedStatement prepareStatement = CONNECTION.prepareStatement(sql);
            setPreparedStatementParams(params, prepareStatement);
            ResultSet rs = prepareStatement.executeQuery();
            CONNECTION.commit();
            return rs;
        }
    }

    private static void setPreparedStatementParams(Object[] params, PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            int j = i + 1; // jdbc index
            if (params[i] instanceof String) {
                ps.setString(j, (String) params[i]);
            } else if (params[i] instanceof Integer) {
                ps.setInt(j, (int) params[i]);
            }
        }
    }

    private static void checkQueryParams(String sql, Object[] params) {
        Objects.requireNonNull(sql);
        Objects.requireNonNull(params);
    }
}

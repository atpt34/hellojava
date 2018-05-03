package com.oleksii.model.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * Class encapsulates single connection to db.
 * Need to use DataSource for connection pooling
 * Statements left open.
 * 
 * @NonThreadSafe
 * @author oleksiiubuntu
 * @version 1.1
 */
public class DBConnection {
    
    private static final String DATABASE_PROPERTIES = "database";
    private static final ResourceBundle DATABASE_BUNDLE = ResourceBundle.getBundle(DATABASE_PROPERTIES);
    private static final String SQLDB_DRIVER = DATABASE_BUNDLE.getString("db.driver");
    private static final String CONNECTION_URL = DATABASE_BUNDLE.getString("db.url");
    private static final String CONNECTION_USER = DATABASE_BUNDLE.getString("db.user");
    private static final String CONNECTION_PASS = DATABASE_BUNDLE.getString("db.password");
    private static final Connection CONNECTION;
    private static final Object LOCK;

    static {
        CONNECTION = Objects.requireNonNull(initConnection());
        LOCK = new Object();
    }

    private DBConnection() {
    }

    private static Connection initConnection() {
        Connection conn = null;
        try {
            Class.forName(SQLDB_DRIVER);
            conn = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USER, CONNECTION_PASS);
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
    
    public static void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            new RuntimeException(e);
        }
    }

    /*
     * If close statement, then resultset will be empty
     */
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
            ResultSet rs = null;
              PreparedStatement prepareStatement = CONNECTION.prepareStatement(sql);
              setPreparedStatementParams(params, prepareStatement);
              rs = prepareStatement.executeQuery();
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
            } else if (params[i] instanceof LocalDate) {
                ps.setDate(j, Date.valueOf(((LocalDate) params[i])));
            }
        }
    }

    private static void checkQueryParams(String sql, Object[] params) {
        Objects.requireNonNull(sql);
        Objects.requireNonNull(params);
    }
    
    private static DataSource dataSource;
    
    static {
        dataSource = Objects.requireNonNull(initDataSource());
    }

    private static DataSource initDataSource() {
        try {
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:comp/env/jdbc/travel");
            System.out.println(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    
    public static DataSource getDataSource() {
        return dataSource;
    }
}

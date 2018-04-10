package com.oleksii.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

//@NonThreadSafe
public class DBConnection {

    private static final String SQLDB_DRIVER = "org.postgresql.Driver";
    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/travel";
    private static final String CONNECTION_USER = "lexa";
    private static final String CONNECTION_PASS= "lexa";
    private static final Connection conn;
    
    static {
        conn = Objects.requireNonNull(initConnection());
    }

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
    
    public static Connection getConnection() {
        return conn;
    }
    
}

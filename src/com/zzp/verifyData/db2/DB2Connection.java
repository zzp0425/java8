package com.zzp.verifyData.db2;

import java.sql.*;

/**
 * Desc
 * Created by zzp
 * on 2016/8/15.17:04
 */
public class DB2Connection {


    private String url = "jdbc:db2://" + DatabaseArgs.IP.getValue() + ":" + DatabaseArgs.PORT.getValue() + "/" + DatabaseArgs.DATABASE.getValue() + ":currentSchema=BIPLOG;";//url为连接字符串;
    private String user = DatabaseArgs.USERNAME.getValue();//数据库用户名;
    private String pwd = DatabaseArgs.PASSWORD.getValue();//数据库密码;

    private static DB2Connection conn;


    static {
        try {
            Class.forName(DatabaseArgs.DRIVER.getValue());//加载db2驱动程序类
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DB2Connection() {

    }

    public static DB2Connection getInstance() {
        if (conn == null) {
            synchronized (DB2Connection.class) {
                if (conn == null) {
                    conn = new DB2Connection();
                }
            }
        }
        return conn;
    }


    public Connection getConnection() {
        Connection connection;
        try {
           // Class.forName(DatabaseArgs.DRIVER.getValue());
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }


    public static void commit(Connection conn) {
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public enum DatabaseArgs {
        DRIVER("com.ibm.db2.jcc.DB2Driver"),
        IP("10.2.1.33"),
        PORT("60000"),
        DATABASE("ywjcdb"),
        SCHEMA("BIPLOG"),
        USERNAME("ywjcusr"),
        PASSWORD("ywjcusr");

        private String value;

        DatabaseArgs(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}

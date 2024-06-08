package db;

import tools.config.ConfigProperties;

import java.sql.*;
import java.util.Map;

public class MySqlDbConnector implements IDBConnector {

    private static Connection connection = null;
    private static Statement statement = null;

    {
        connect(); //блок нестатической инициализации, будет вызван до контруктора
    }

    private void connect() { //метод подключения к бд

        Map<String, String> config = new ConfigProperties().getConfig();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", config.get("url"), config.get("db_name")), config.get("db_username"), config.get("db_password"));
            }
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void execute(String sqlQuery) {
        try {
            statement.execute(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet executeResult(String sqlQuery) {
        try {
            return statement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


}

package db;

import tools.config.ConfigProperties;
import tools.config.IConfig;

import java.sql.*;
import java.util.Map;

public class MySqlDbConnector implements IDBConnector {

    private static Connection connection = null;
    private static Statement statement = null;

    {
        connect(); //блок нестатической инициализации, будет вызван до контруктора
    }

    private void connect() {
        IConfig config = new ConfigProperties();
        Map<String, String> settings = config.getConfig();
        if (connection == null){
            try {
                connection = DriverManager
                        .getConnection(settings.get("url") + "/" + settings.get("db_name"),
                                settings.get("db_username"),
                                settings.get("db_password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement == null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void execute(String response) {
        try {
            statement.execute(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeResult(String response) {
        try {
            return statement.executeQuery(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

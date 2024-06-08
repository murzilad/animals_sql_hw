package db;

import java.sql.ResultSet;

public interface IDBConnector {
    void execute(String sqlQuery); //метод создания таблиц
    ResultSet executeResult(String sqlQuery); //возвращает результат
    void close();
}

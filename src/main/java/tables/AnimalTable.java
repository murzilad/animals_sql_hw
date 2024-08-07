package tables;

import db.MySqlDbConnector;
import objects.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class AnimalTable extends AbsTable {

    public AnimalTable() {
        super("animals");
        columns = new HashMap<>();
        columns.put("id", "int PRIMARY KEY AUTO_INCREMENT");
        columns.put("type", "varchar(15)");
        columns.put("name", "varchar(15)");
        columns.put("age", "int");
        columns.put("weight", "int");
        columns.put("color", "varchar(15)");
        create();
    }

    public ArrayList<Animal> selectAll() {
        String sqlQuery = String.format("SELECT * FROM %s", tableName);
        return selectByQuery(sqlQuery);
    }

    public ArrayList<Animal> selectByType(String type) {
        String sqlQuery = String.format("SELECT * FROM %s WHERE type = '%s'", tableName, type);
        return selectByQuery(sqlQuery);
    }

    private ArrayList<Animal> selectByQuery(String sqlQuery) {
        ArrayList<Animal> animals = new ArrayList<>();
        dbConnector = new MySqlDbConnector();
        ResultSet resultSet = dbConnector.executeResult(sqlQuery);
        try {
            while (resultSet.next()) {
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setType(resultSet.getString("type"));
                animal.setName(resultSet.getString("name"));
                animal.setAge(resultSet.getInt("age"));
                animal.setWeight(resultSet.getInt("weight"));
                animal.setColor(resultSet.getString("color"));
                animals.add(animal);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbConnector.close();
        }
        return animals;
    }

    public void delete(long id){
        dbConnector = new MySqlDbConnector();
        String sqlQuery = String.format("DELETE FROM %s WHERE id='%d'", tableName, id);
        dbConnector.execute(sqlQuery);
        dbConnector.close();
    }

    public void insert(Animal animal){
        dbConnector = new MySqlDbConnector();
        String sqlQuery = String.format("INSERT INTO %s (type, name, age, weight, color) " +
                        "VALUES ('%s', '%s', '%d', '%d', '%s')",
                tableName, animal.getType(), animal.getName(),
                animal.getAge(), animal.getWeight(), animal.getColor());
        dbConnector.execute(sqlQuery);
        dbConnector.close();
    }

    public void update(Animal animal){
        dbConnector = new MySqlDbConnector();
        String sqlQuery = String.format("UPDATE %s SET " +
                        "type='%s', name='%s', age='%d', weight='%d', color='%s' WHERE id = %d ",
                tableName,
                animal.getType(),
                animal.getName(),
                animal.getAge(),
                animal.getWeight(),
                animal.getColor(),
                animal.getId());
        dbConnector.execute(sqlQuery);
        dbConnector.close();
    }
}

package tables;

import animals.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnimalTable extends AbsTable {

    public AnimalTable(String tableName) {
        super("animal");

    }

    public List<Animal> read(Map<String, String> columns) throws SQLException {
        List<Animal> animals = new ArrayList<>();
        ResultSet resultSet = super.read();

        while (resultSet.next()) {
            Animal animal = new Animal();

            animal.setId(resultSet.getInt("id"));
            animals.add(animal);

        }
        return animals;
    }

}

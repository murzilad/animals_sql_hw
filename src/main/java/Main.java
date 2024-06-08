import animals.Animal;
import data.AnimalData;
import data.CommandsData;
import db.MySqlDbConnector;
import factory.AnimalFactory;
import sun.security.mscapi.CPublicKey;
import tables.AnimalTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.printf("Выберите одну из команд: add/list/exit\n");
            String answer = scanner.next().toUpperCase().trim();

            //валидируем вводимую команду и сохраняем ее в переменную commandsData, если валидна
            CommandsData commandsData = null;
            boolean isCommandCorrect = false;
            for(CommandsData commands : CommandsData.values())
                if(commands.name().equals(answer)) {
                    isCommandCorrect = true;
                    commandsData = CommandsData.valueOf(answer);
                    break;
                }
            if (!isCommandCorrect) {
                System.out.printf("Неверная команда\n");
                continue;
            }

            switch (commandsData) {
                case ADD: {
                    while (true) {
                        System.out.printf("Выберите животное: cat/dog/duck\n");
                        String animalType = scanner.next().toUpperCase().trim();

                        //валидируем вводимую команду и сохраняем ее в переменную animalData, если валидна
                        AnimalData animalData = null;
                        boolean isAnimalTypeCorrect = false;

                        for (AnimalData animal : AnimalData.values())
                            if (animal.name().equals(animalType)) {
                                isAnimalTypeCorrect = true;
                                animalData = AnimalData.valueOf(animalType);
                                break;
                            }
                        if (!isAnimalTypeCorrect) {
                            System.out.printf("Такого животного не существует\n");
                            continue;
                        }
                        Animal createdAnimal = new AnimalFactory(scanner).create(animalData); //создание экземпляра класса Animal

                        createdAnimal.setName();
                        createdAnimal.setAge();
                        createdAnimal.setWeight();
                        createdAnimal.setColor();

                        createdAnimal.say();
                        animals.add(createdAnimal);
                        break;
                    }
                    break;
                }

                case LIST: {
                    if (animals.isEmpty()) {
                        System.out.println("Список животных пуст");
                        break;
                    }

                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                }
                case EXIT: {
                    System.out.println("Пока-пока");
                    System.exit(0); //выход из программы без ошибок (код 0)
                }
            }
        }
    }
}


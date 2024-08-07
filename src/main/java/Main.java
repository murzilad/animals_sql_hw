import animals.utils.Validation;
import objects.Animal;
import data.AnimalData;
import data.CommandsData;
import factory.AnimalFactory;
import tables.AnimalTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Validation validation = new Validation();
        AnimalTable animalTable = new AnimalTable();

        while (true) {
            System.out.printf("Выберите одну из команд: add/list/update/filter/delete/exit\n");
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
                        createdAnimal.setType(animalType);

                        String name = "";
                        while (true) {
                            System.out.println("Введите имя животного");
                            name = scanner.next().trim();
                            if (validation.isChars(name)) {
                                break;
                            }
                            System.out.println("Неверное имя животного");
                        }
                        createdAnimal.setName(name);

                        String age = "";
                        while (true) {
                            System.out.printf("Введите возраст животного\n");
                            age = scanner.next().trim();
                            if (validation.isNumber(age) && Integer.parseInt(age) > 0 && age.length() <= 6) { //валидация + проверка на отриц значение и ограничение на ввод не больше 6 символов
                                break;
                            }
                            System.out.println("Введен неверный возраст животного");
                        }
                        createdAnimal.setAge(Integer.parseInt(age));

                        String weight = "";
                        while (true) {
                            System.out.printf("Введите вес животного\n");
                            weight = scanner.next().trim();
                            if (validation.isNumber(weight) && Integer.parseInt(weight) > 0 && weight.length() <= 6) { //валидация + проверка на отриц значение и ограничение на ввод не больше 6 символов
                                break;
                            }
                            System.out.println("Введен неверный вес животного");
                        }
                        createdAnimal.setWeight(Integer.parseInt(weight));

                        String color = "";
                        while (true) {
                            System.out.printf("Введите цвет животного\n");
                            color = scanner.next().trim();
                            if (validation.isChars(color)) {
                                break;
                            }
                            System.out.println("Введен несуществующий цвет животного");
                        }
                        createdAnimal.setColor(color);

                        animalTable.insert(createdAnimal);
                        createdAnimal.say();
                        animals.add(createdAnimal);
                        break;
                    }
                    break;
                }

                case LIST: {
                    animals = animalTable.selectAll();

                    if (animals.isEmpty()) {
                        System.out.println("Список животных пуст");
                        break;
                    }

                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                }

                case UPDATE: {
                    while (true) {
                        Animal animalUpdate = new Animal();
                        String id = "";
                        while (true) {
                            System.out.printf("Введите ID животного для редактирования\n");
                            id = scanner.next().trim();
                            if (validation.isNumber(id) && Integer.parseInt(id) > 0 && id.length() <= 6) {
                                break;
                            }
                            System.out.println("Введен неверный id животного");
                        }
                        animalUpdate.setId(Integer.parseInt(id));

                        System.out.printf("Введите новый тип животного: cat/dog/duck\n");
                        String animalType = scanner.next().toUpperCase().trim();

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

                        animalUpdate.setType(animalType);

                        String animalName = "";
                        while (true) {
                            System.out.printf("Введите новое имя животного\n");
                            animalName = scanner.next().toUpperCase().trim();
                            if (validation.isChars(animalName)) {
                                break;
                            }
                            System.out.println("Неверное имя животного");
                        }
                        animalUpdate.setName(animalName);

                        String age = "";
                        while (true) {
                            System.out.printf("Введите новый возраст\n");
                            age = scanner.next().toUpperCase().trim();
                            if (validation.isNumber(age) && Integer.parseInt(age) > 0 && age.length() <= 6) { //валидация + проверка на отриц значение и ограничение на ввод не больше 6 символов
                                break;
                            }
                            System.out.println("Введен неверный возраст животного");
                        }
                        animalUpdate.setAge(Integer.parseInt(age));

                        String weight = "";
                        while (true) {
                            System.out.printf("Введите новый вес\n");
                            weight = scanner.next().toUpperCase().trim();
                            if (validation.isNumber(weight) && Integer.parseInt(weight) > 0 && weight.length() <= 6) { //валидация + проверка на отриц значение и ограничение на ввод не больше 6 символов
                                break;
                            }
                            System.out.println("Введен неверный вес животного");
                        }
                        animalUpdate.setWeight(Integer.parseInt(weight));

                        String color = "";
                        while (true) {
                            System.out.printf("Введите новый цвет\n");
                            color = scanner.next().toUpperCase().trim();
                            if (validation.isChars(color)) {
                                break;
                            }
                            System.out.println("Введен несуществующий цвет животного");
                        }
                        animalUpdate.setColor(color);

                        animalTable.update(animalUpdate);
                        System.out.println("Животное успешно отредактировано");

                        break;
                    }
                    break;
                }

                case FILTER: {
                    while (true) {
                        System.out.printf("Введите тип животного для применения фильтрации: cat/dog/duck\n");
                        String animalType = scanner.next().toUpperCase().trim();

                        AnimalData animalData = null;
                        boolean isAnimalTypeCorrect = false;

                        for (AnimalData animal : AnimalData.values()) {
                            if (animal.name().equals(animalType)) {
                                isAnimalTypeCorrect = true;
                                animalData = AnimalData.valueOf(animalType);
                                break;
                            }
                        }

                        if (!isAnimalTypeCorrect) {
                            System.out.printf("Такого животного не существует\n");
                            continue;
                        }

                        ArrayList<Animal> filter = animalTable.selectByType(animalType);

                        if (filter.isEmpty()) {
                            System.out.println("Отсутствуют данные по животным выбранного типа");
                        }

                        for (Animal animal : filter) {
                            System.out.println(animal.toString());
                        }
                        break;
                    }
                    break;
                }

                case DELETE: {
                    String id = "";
                    while (true) {
                        System.out.printf("Введите ID животного для удаления\n");
                        id = scanner.next().trim();
                        if (validation.isNumber(id) && Integer.parseInt(id) > 0 && id.length() <= 6) {
                            break;
                        }
                        System.out.println("Введен неверный id животного");
                    }
                    animalTable.delete(Long.parseLong(id));
                    System.out.println("Животное успешно удалено");
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


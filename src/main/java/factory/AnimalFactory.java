package factory;

import animals.Animal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalData;

import java.util.Scanner;

public class AnimalFactory {

    private Scanner scanner = null;

    public AnimalFactory(Scanner scanner) {
        this.scanner = scanner;
    }

    public Animal create(AnimalData animalData) {
        switch (animalData) {

            case CAT: {
                return new Cat(scanner);
            }
            case DOG: {
                return new Dog(scanner);
            }
            case DUCK: {
                return new Duck(scanner);
            }
        }
        return null;
    }
}

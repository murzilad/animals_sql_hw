package factory;

import objects.Animal;
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
//                return new Cat(scanner);
                return new Cat();
            }
            case DOG: {
//                return new Dog(scanner);
                return new Dog();
            }
            case DUCK: {
//                return new Duck(scanner);
                return new Duck();
            }
        }
        return null;
    }
}

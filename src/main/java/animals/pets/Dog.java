package animals.pets;

import objects.Animal;

public class Dog extends Animal {

//    public Dog(Scanner scanner) {
//        super(scanner);
//    }

    //переопределяем метод say из родительского класса Animal
    @Override
    public void say() {
        System.out.println("Гав");
    }
}

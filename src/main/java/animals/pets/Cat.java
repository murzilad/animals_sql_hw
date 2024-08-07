package animals.pets;

import objects.Animal;

public class Cat extends Animal {

//    public Cat(Scanner scanner) {
//        super(scanner);
//    }

    //переопределяем метод say из родительского класса Animal
    @Override
    public void say() {
        System.out.println("Мяу");
    }

}

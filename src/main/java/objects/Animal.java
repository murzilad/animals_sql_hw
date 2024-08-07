package objects;

public class Animal {

    private int id, age, weight;
    private String name, color, type;

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

//    @Override
//    public String toString() {
//        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s", name, age, getPadej(age), weight, color);
//    }

    @Override
    public String toString() {
        return "Animal [" +
                "id = " + id +
                ", type = '" + type + '\'' +
                ", name = '" + name + '\'' +
                ", color = '" + color + '\'' +
                ", weight = " + weight +
                ", age= " + age +
                ']';
    }


    public String getPadej(int age) {

        int remainder = age % 10;

        if (age >= 11 && age <= 14) {
            return "лет";
        }

        if (remainder == 1 && age < 110) {
            return "год";
        }
        if (remainder >= 5) {
            return "лет";
        }
        if (remainder >= 2) {
            return "года";
        }
        return "лет";

    }
}

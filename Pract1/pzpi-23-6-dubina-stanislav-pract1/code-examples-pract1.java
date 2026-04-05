interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Проста кава";
    }

    public double getCost() {
        return 10.0;
    }
}

abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", молоко";
    }

    public double getCost() {
        return decoratedCoffee.getCost() + 3.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", цукор";
    }

    public double getCost() {
        return decoratedCoffee.getCost() + 1.5;
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee c = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(c.getDescription()); // Проста кава, молоко, цукор
        System.out.println(c.getCost());         // 14.5
    }
}
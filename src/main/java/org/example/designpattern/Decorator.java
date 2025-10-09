package org.example.designpattern;

interface Coffee
{
    int getCost();
}

class SimpleCoffee implements Coffee
{
    public int getCost()
    {
        return 10;
    }
}

abstract class CoffeeDecorator implements Coffee
{
    Coffee coffee;
    CoffeeDecorator(Coffee coffee)
    {
        this.coffee = coffee;
    }
    public int getCost()
    {
        return coffee.getCost();
    }
}

class MilkDecorator extends CoffeeDecorator
{

    MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    public int getCost()
    {
        return super.getCost()+10;
    }
}

class SugarDecorator extends CoffeeDecorator
{

    SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    public int getCost()
    {
        return super.getCost()+10;
    }
}

public class Decorator
{
    public static void main(String[] args) {
        Coffee coffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(coffee.getCost());
    }
}
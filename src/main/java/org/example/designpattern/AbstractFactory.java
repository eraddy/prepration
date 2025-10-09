package org.example.designpattern;

import java.lang.reflect.Field;

interface Fries
{
    void prepare();
}

interface Pizza
{
    void prepare();
}

class CheeseFries implements Fries
{
    @Override
    public void prepare(){
        System.out.println("Cheese fries ready");
    }
}
class VegFries implements Fries{
    @Override
    public void prepare()
    {
        System.out.println("Peri peri fries ready");
    }
}

class CheesePizza implements Pizza{
    @Override
    public void prepare()
    {
        System.out.println("Cheese pizza ready");
    }
}

class VegPizza implements Pizza{
    @Override
    public void prepare()
    {
        System.out.println("Veg Pizza ready");
    }
}

interface FoodFactory
{
    Fries createFries();
    Pizza createPizza();
}

class CheeseFactory implements FoodFactory{
    public Fries createFries()
    {
        return new CheeseFries();
    }
    public Pizza createPizza()
    {
        return new CheesePizza();
    }
}

class VegFactory implements FoodFactory
{
    public Fries createFries()
    {
        return new VegFries();
    }
    public Pizza createPizza()
    {
        return new VegPizza();
    }
}

class Food
{
    public static FoodFactory getFoodFactory(String s)
    {
        if(s.equals("veg"))
            return new VegFactory();
        else
            return new CheeseFactory();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        FoodFactory factory = Food.getFoodFactory("veg");
        Fries fries = factory.createFries();
    }
}

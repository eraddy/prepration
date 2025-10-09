package org.example.designpattern;

interface Burger
{
    void bake();
}

class CheeseBurger implements Burger
{
    public void bake()
    {
        System.out.println("Cheese burger produced");
    }
}

class VegBurger implements Burger{
    public void bake()
    {
        System.out.println("Veg burger ready");
    }
}

class BurgerFactory
{
    public static Burger getBurger(String s) {
        if (s.equals("Cheese"))
            return new CheeseBurger();
        else if (s.equals("Veg"))
            return new VegBurger();
        return null;
    }

}

public class Factory {
    public static void main(String[] args) {
        Burger burger = BurgerFactory.getBurger("Veg");
        burger.bake();
    }
}

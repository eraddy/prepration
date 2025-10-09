package org.example.core;

interface GenericOnTheWay<T>
{
    int n = 100;
}

public class GenericPractice<T extends Number> {
    public T number;
    GenericPractice(T t)
    {
        this.number = t;
    }

    public static void main(String[] args) {
     int a = 10;
     double b = 1.03;
     GenericPractice<? super Number> genericPractice = new GenericPractice<>(a);
     genericPractice.number = b;
     System.out.println(genericPractice.number);
    }
}

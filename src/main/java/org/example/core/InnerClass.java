package org.example.core;

public class InnerClass {
    public static class Inner
    {
        public String name;
    }


    public static void main(String[] args) {
        InnerClass outer = new InnerClass();
        Inner inner = new Inner();
        outer.main(12);
    }

    public static void main(int a) {
        System.out.println(a);
    }

}

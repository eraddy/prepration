package org.example.streams;

import java.awt.image.ImageProducer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams2 {
    // Numbers
    public static List<Integer> numbers = Arrays.asList(12, 45, 67, 23, 89, 90, 34, 56, 78, 99, 100);

    // Cities
    public static List<String> cities = Arrays.asList("Delhi", "Mumbai", "Bangalore", "Chennai", "Kolkata", "Pune", "Hyderabad");

    // Orders
    static class Order {
        int orderId;
        String customer;
        double amount;
        String status;

        Order(int orderId, String customer, double amount, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        public String toString() {
            return customer + " - " + amount + " (" + status + ")";
        }
    }

    public static List<Order> orders = Arrays.asList(
            new Order(101, "Aditya", 2500, "DELIVERED"),
            new Order(102, "Garvita", 1500, "CANCELLED"),
            new Order(103, "Rahul", 8000, "DELIVERED"),
            new Order(104, "Priya", 1200, "PENDING"),
            new Order(105, "Rohit", 5000, "DELIVERED"),
            new Order(106, "Ankit", 2000, "DELIVERED"),
            new Order(107, "Garima", 3000, "CANCELLED")
    );

    // Books
    static class Book {
        String title;
        String author;
        double price;
        int year;

        Book(String title, String author, double price, int year) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.year = year;
        }

        public String toString() {
            return title + " by " + author + " (" + year + ")";
        }
    }

    public static List<Book> books = Arrays.asList(
            new Book("Effective Java", "Joshua Bloch", 50, 2018),
            new Book("Java Concurrency in Practice", "Brian Goetz", 55, 2006),
            new Book("Spring in Action", "Craig Walls", 45, 2020),
            new Book("Head First Java", "Kathy Sierra", 35, 2005),
            new Book("Clean Code", "Robert Martin", 60, 2008),
            new Book("Domain-Driven Design", "Eric Evans", 70, 2003)
    );


    public static void main(String[] args) {


        System.out.println(numbers.stream().filter(i -> i > 50).toList());
        System.out.println(numbers.stream().max(Comparator.comparingInt(i -> i)).get());
        System.out.println(cities.stream().filter(s -> s.length() > 5).toList());
        System.out.println(cities.stream().map(String::toUpperCase).toList());
        System.out.println(cities.stream().sorted(Comparator.reverseOrder()).toList());
        System.out.println(orders.stream().map(o -> o.customer).toList());
        books.stream().forEach(e -> System.out.print(e.title + ", "));
        System.out.println();
        System.out.println(orders.stream().filter(e -> e.status.equals("DELIVERED")).count());
        System.out.println(books.stream().min(Comparator.comparingDouble(b -> b.price)).orElse(new Book("jgsdh","ksu",0,0)));
        System.out.println(books.stream().min(Comparator.comparingInt(b -> b.year)).get());
        System.out.println((Double) orders.stream().filter(b -> b.status.equals("DELIVERED")).mapToDouble(o -> o.amount).sum());
        double asDouble = books.stream().mapToDouble(b -> b.price).average().getAsDouble();
        System.out.println(books.stream().collect(Collectors.groupingBy(b -> b.author)));

    }
}

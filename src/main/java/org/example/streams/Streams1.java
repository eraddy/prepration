package org.example.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Streams1 {
    // Simple numbers
    public static List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // Strings
    public static List<String> names = Arrays.asList("Aditya", "Garvita", "Rahul", "Priya", "Ankit", "Garima", "Rohit");

    // Employees
    static class Employee {
        int id;
        String name;
        String department;
        double salary;
        int age;
        List<Employee> friends;

        Employee(int id, String name, String department, double salary, int age) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }

        public String toString() {
            return name + " (" + department + ", " + salary + ", " + age + ")";
        }
    }

    public static List<Employee> employees = Arrays.asList(
            new Employee(1, "Aditya", "IT", 60000, 25),
            new Employee(2, "Garvita", "HR", 50000, 24),
            new Employee(3, "Rahul", "Finance", 70000, 30),
            new Employee(4, "Priya", "IT", 80000, 28),
            new Employee(5, "Rohit", "Finance", 40000, 26),
            new Employee(6, "Ankit", "HR", 45000, 23),
            new Employee(7, "Garima", "IT", 90000, 35)
    );

    // Products
    static class Product {
        int id;
        String name;
        String category;
        double price;

        Product(int id, String name, String category, double price) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String toString() {
            return name + " (" + category + ", " + price + ")";
        }
    }

    public static List<Product> products = Arrays.asList(
            new Product(101, "Laptop", "Electronics", 1200),
            new Product(102, "Phone", "Electronics", 800),
            new Product(103, "Shirt", "Clothing", 40),
            new Product(104, "Jeans", "Clothing", 60),
            new Product(105, "TV", "Electronics", 1500),
            new Product(106, "Shoes", "Footwear", 100),
            new Product(107, "Sandals", "Footwear", 50)
    );

    public static void main(String[] args) {
        System.out.println(numbers.stream().filter(i -> i % 2 == 0).toList());
        System.out.println(numbers.stream().map(x -> x * x).toList());
        System.out.println(numbers.stream().reduce(Integer::sum));
        System.out.println(names.stream().filter(s -> s.charAt(0) == 'A').toList());
        System.out.println(names.stream().map(String::toUpperCase).toList());
        System.out.println(names.stream().sorted().toList());
        System.out.println(numbers.stream().filter(x -> x > 5).findFirst());
        System.out.println((Long) names.stream().filter(s -> s.contains("a")).count());
        System.out.println(employees.stream().map(e -> e.name).toList());
        System.out.println(products.stream().map(p -> p.name).toList());

        System.out.println(employees.stream().filter(e -> e.department.equals("IT")).toList());
        System.out.println(employees.stream().filter(e -> e.salary > 50000).toList());
        System.out.println(employees.stream().map(e -> e.department).distinct().toList());
        System.out.println(employees.stream().max(Comparator.comparing(e->e.salary))); // solve by Collectors.maxBy()
        System.out.println(employees.stream().max(Comparator.comparingDouble(p -> p.salary))); // solved
        System.out.println(employees.stream().collect(Collectors.averagingDouble(e -> e.salary)));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.department)));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.department)));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.department,Collectors.counting())));
        System.out.println(employees.stream().sorted(Comparator.comparingInt(e -> e.age)).toList());
        System.out.println(products.stream().filter(p -> p.category.equals("Electronics") && p.price > 1000).toList());
        System.out.println(products.stream().min(Comparator.comparingDouble(p -> p.price)).orElse(new Product(0,null,null, 0)));
        System.out.println(products.stream().max(Comparator.comparingDouble(p -> p.price)).orElse(new Product(0,null,null, 0)));
        System.out.println((Double) products.stream().mapToDouble(p -> p.price).sum());
        System.out.println(products.stream().collect(Collectors.groupingBy(p -> p.category)));
        System.out.println(products.stream().collect(Collectors.groupingBy(p -> p.category,Collectors.counting())));
        System.out.println(numbers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst());

        System.out.println(employees.stream().sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed()).limit(3));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.collectingAndThen(Collectors.toList(), l -> l.stream().min(Comparator.comparingDouble(e -> e.age))))));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.averagingDouble(e -> e.salary))));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.salary > 60000)));
        System.out.println(employees.stream().sorted(Comparator.comparingDouble((Employee e) -> e.salary).reversed()).map(e -> e.name).toList());
        System.out.println(products.stream().filter(p -> p.category.equals("Electronics")).sorted(Comparator.comparingDouble((Product p) -> p.price).reversed()).skip(1).findFirst());
        System.out.println(products.stream().collect(Collectors.groupingBy(p -> p.category, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(p -> p.name).toList()))));
        System.out.println(employees.stream().collect(Collectors.groupingBy(e -> e.name, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(e -> e.salary).toList()))));
        //35 36 40  are not done ill ask you about them later
        System.out.println(employees.stream()
                .collect(Collectors.groupingBy(e -> e.department, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().collect(Collectors.averagingDouble(e->e.salary)))))
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
        );

        System.out.println(employees.stream().filter(e -> e.department.equals("IT")).max(Comparator.comparingDouble((Employee e) -> e.salary)));
        System.out.println(employees.stream().filter(e -> e.name.charAt(0) == 'G' && e.age > 50).toList());
        List<List<Integer>> list = new ArrayList<>();

        list.stream().flatMap(List::stream).toList();









        Map<Employee,Integer> map = new HashMap<>();


        employees.stream().map(e -> e.friends).forEach(emp -> {
            emp.stream().distinct().forEach(friend -> {
                map.put(friend,map.getOrDefault(friend,0)+1);
            });
        });

        map.entrySet().stream().filter(entry -> entry.getValue()>=employees.size()).map(Map.Entry::getKey).toList();


    }


}

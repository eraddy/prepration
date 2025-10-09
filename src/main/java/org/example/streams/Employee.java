package org.example.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getDepartment()
    {
        return department;
    }

    public int getYearOfJoining()
    {
        return yearOfJoining;
    }

    public double getSalary()
    {
        return salary;
    }

    @Override
    public String toString()
    {
        return "Id : "+id
                +", Name : "+name
                +", age : "+age
                +", Gender : "+gender
                +", Department : "+department
                +", Year Of Joining : "+yearOfJoining
                +", Salary : "+salary;
    }

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

//        System.out.println(employeeList.stream().collect(Collectors.groupingBy((Employee e) -> e.gender, Collectors.counting())));
//        System.out.println();
//        employeeList.stream().map(e -> e.department).distinct().forEach(System.out::println);
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(e -> e.gender, Collectors.averagingInt(e -> e.age))));
//        System.out.println();
//        System.out.println(employeeList.stream().max(Comparator.comparingDouble(e -> e.salary)).orElse(null));
//        System.out.println();
//        employeeList.stream().filter(e -> e.yearOfJoining > 2015).map(e -> e.name).forEach(e -> System.out.print(e+" "));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(e -> e.department, Collectors.counting())));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))));
//        System.out.println();
//        System.out.println(employeeList.stream().filter(e -> e.department.equals("Product Development")).min(Comparator.comparingInt(Employee::getAge)));
//        System.out.println();
//        String s = "Java Main In The lives of the nation";
//        Map<Character,Integer> map = new HashMap<>();
//        for(char c : s.toCharArray())
//        {
//            if(c == ' ')
//                continue;
//            else
//                map.put(c,map.getOrDefault(c,0)+1);
//        }
//        System.out.println(map);
//        System.out.println();
//        System.out.println(employeeList.stream().filter(e -> e.getDepartment().equalsIgnoreCase("Sales and Marketing")).collect(Collectors.groupingBy(Employee::getGender, Collectors.counting())));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(Employee::getName).toList()))));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary)));
//        System.out.println();
//        System.out.println(employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() <= 25)));
//        System.out.println();
//        employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).ifPresent(employee -> System.out.println(employee.getAge() + " " + employee.getDepartment()));
//        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList()))));

        String s = "JAVA AND JAVA";
        Set<Character> set = new LinkedHashSet<>();
        set.clear();
        System.out.println(s.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .get()
                .getKey());

    }
}
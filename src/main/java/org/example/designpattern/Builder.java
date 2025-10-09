package org.example.designpattern;

class Employee
{
    private int age;
    private String name;
    private String address;
    private int salary;
    private int cast;
    private int region;
    private int nation;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }

    public int getCast() {
        return cast;
    }

    public int getRegion() {
        return region;
    }

    public int getNation() {
        return nation;
    }

    public Employee(int age, String name, String address, int salary, int cast, int region, int nation) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.cast = cast;
        this.region = region;
        this.nation = nation;
    }
}

class EmployeeBuilder
{
    private int age;
    private String name;
    private String address;
    private int salary;
    private int cast;
    private int region;
    private int nation;

    public EmployeeBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public EmployeeBuilder setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeBuilder setCast(int cast) {
        this.cast = cast;
        return this;
    }

    public EmployeeBuilder setRegion(int region) {
        this.region = region;
        return this;
    }

    public EmployeeBuilder setNation(int nation) {
        this.nation = nation;
        return this;
    }

    public Employee build()
    {
        return new Employee(age,name,address,salary,cast,region,nation);
    }
}

public class Builder {
    public static void main(String[] args) {
        Employee employee = new EmployeeBuilder()
                .setNation(8753)
                .setAge(34)
                .setCast(3)
                .setSalary(34)
                .setName("ljca")
                .setAddress("safa")
                .setRegion(24)
                .build();

        System.out.println(employee.getAddress());

    }
}

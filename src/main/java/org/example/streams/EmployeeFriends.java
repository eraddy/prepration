package org.example.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFriends {

    List<EmployeeFriends> firends = new ArrayList<>();

    public static void main(String[] args) {
        List<EmployeeFriends> employeeList = new ArrayList<>();

        Map<EmployeeFriends,Integer> map = new HashMap<>();

        employeeList.stream().forEach(
                emp -> emp.firends.stream().distinct().forEach(e -> map.put(e,map.getOrDefault(e,0)+1))
        );
        System.out.println(map.entrySet().stream().filter(set -> set.getValue() == employeeList.size()).toList());
    }
}

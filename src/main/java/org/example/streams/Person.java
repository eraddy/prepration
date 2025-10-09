package org.example.streams;

import java.util.List;

public record Person(int age, String name, int salary, List<Person> friends) {
}

package org.example.jpa;

import org.example.dsa.PascalTriangle;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortCharacterByFrequency {
    public String sortCharacterByFrequency(String str)
    {
        if(str == null || str.isEmpty())
            return "";
        return str.chars()
                .mapToObj(i -> (char)i)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong((Map.Entry<Character,Long> entry) -> entry.getValue()).reversed())
                .map(e -> e.getKey().toString().repeat(e.getValue().intValue()))
                .collect(Collectors.joining());
    }
    public static void main(String[] args) {
        SortCharacterByFrequency sortCharacterByFrequency = new SortCharacterByFrequency();
        System.out.println(sortCharacterByFrequency.sortCharacterByFrequency("tree"));
    }
}

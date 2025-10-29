package org.example.jpa;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OccurrenceCounter {
    public Map<String, Long> occurrenceCounter(List<String> list)
    {
        if(list == null || list.isEmpty())
            return new HashMap<>();
        Map<String,Long> ans = new LinkedHashMap<>();
        list
                .stream()
                .flatMap(s -> Arrays.stream(s
                        .replaceAll("[^a-zA-Z]"," ")
                        .toLowerCase()
                        .split(" ")))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong((Map.Entry<String,Long> entry) -> entry.getValue()).reversed())
                .forEach(e -> {
                    ans.put(e.getKey(), e.getValue());
                });
        return ans;
    }

    public static void main() {
        OccurrenceCounter occurrenceCounter = new OccurrenceCounter();
        System.out.println(occurrenceCounter.occurrenceCounter(List.of( "Apple and banana are fruits.", "I like to eat an apple every day.", "Orange is also a fruit, but banana is my favorite.", "APPLE pie is delicious!")));
    }
}

package org.example.jpa;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostFrequentOccurringElement {
    public int findMostFrequentValidElement(int[] arr,int threshold) // threshold is mentioned in the question
    {
        if (arr == null || arr.length == 0 || threshold <= 0) return -1; //Null handled

        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() <= threshold) // every element with frequency higher than threshold is removed
                .sorted((n,m) -> {
                    int freqCompare = n.getValue().compareTo(m.getValue()); // the highest frequency
                    if(freqCompare == 0)
                        return n.getKey().compareTo(m.getKey()); // smallest in case of same frequency
                    return freqCompare;

                })
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1); // -1 in case of no such element
    }
}

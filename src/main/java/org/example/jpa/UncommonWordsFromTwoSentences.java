package org.example.jpa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UncommonWordsFromTwoSentences {

    public String[] uncommonWordsFromTwoSentences(String s1, String s2) {
        return Arrays.stream((s1 + " " + s2)
                        .replaceAll("[^a-zA-Z]", " ")
                        .toLowerCase()
                        .trim()
                        .split("\\s+")) // handles multiple spaces
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .toArray(String[]::new); // return array, not List
    }

    public static void main(String[] args) {
        UncommonWordsFromTwoSentences uw = new UncommonWordsFromTwoSentences();
        System.out.println(Arrays.toString(uw.uncommonWordsFromTwoSentences(
                "this apple is sweet.",
                "this apple is sour."
        )));
    }
}

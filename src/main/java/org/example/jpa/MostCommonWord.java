package org.example.jpa;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MostCommonWord {
    public String mostCommonWord(String paragraph,String[] banned)
    {
        if(paragraph == null)
            return "";
        Set<String> ban = new HashSet<>(List.of(banned));
        return Arrays.stream(paragraph
                        .toLowerCase()
                        .replaceAll("[^a-zA-Z]", " ")
                        .split(" "))
                .filter(word -> !ban.contains(word))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingLong((Map.Entry<String,Long> entry) -> entry.getValue()).reversed())
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

    }

    public static void main(String[] args) {
        MostCommonWord mostCommonWord = new MostCommonWord();
        System.out.println(mostCommonWord.mostCommonWord("a.",new String[]{}));
    }
}

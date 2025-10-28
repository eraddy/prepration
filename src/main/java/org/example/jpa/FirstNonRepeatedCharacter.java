package org.example.jpa;


import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class FirstNonRepeatedCharacter {
    public String findFirstNonRepeatedCharacter(String str)
    {
        if(str == null)
            return "No non-repeated character found";
        str = str.toLowerCase();
        Character ans =  str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(
                        c -> c,
                        LinkedHashMap::new,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        if(ans == null)
            return "No non-repeated character found";
        return String.valueOf(ans);
    }

    static void main() {
        FirstNonRepeatedCharacter firstNonRepeatedCharacter = new FirstNonRepeatedCharacter();
        String str = "JAVA AND JAVA";
        String ans = firstNonRepeatedCharacter.findFirstNonRepeatedCharacter(str);
        System.out.println(ans);
    }
}

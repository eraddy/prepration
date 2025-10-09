package org.example.streams;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Stream3 {
    public static void main(String[] args) {
        int[] arr = {5,3,9,9,10,5,3,2};
        int k = 1;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i : arr)
        {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        System.out.println(map.entrySet().stream()
                .filter(e -> e.getValue() <= k)
                .sorted(Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getKey()).reversed())
                .skip(1)
                .findFirst()
                .get()
                .getKey());

    }
}

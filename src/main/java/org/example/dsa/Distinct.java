package org.example.dsa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Distinct {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,2,7};

        List<Integer> list = new ArrayList<>();

        for(int i : arr1)
            list.add(i);

        for (int i : arr2)
            list.add(i);

        list.stream().distinct().map(i -> (int) i).toArray();
        int[] array = list.stream().distinct().mapToInt(i->i).sorted().toArray();


    }
}

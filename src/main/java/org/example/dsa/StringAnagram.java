package org.example.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringAnagram {
    public boolean isAnagramWithMap(String s, String t) {
        if(s.length()!=t.length())
            return false;
        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for(int i = 0;i<s.length();i++)
        {
            map1.put(s.charAt(i),map1.getOrDefault(s.charAt(i),0)+1);
            map2.put(t.charAt(i),map2.getOrDefault(t.charAt(i),0)+1);
        }

        return map1.equals(map2);
    }
    public boolean isAnagramOptimized(String s, String t)
    {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1,arr2);
    }
}

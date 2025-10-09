package org.example.jpa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SecondLargestUniqueElement {

    public int secondLargestUniqueElement(int[] arr,int k)
    {
        if(arr == null || arr.length==0)
            return -1;

        List<Integer> list = new ArrayList<>(Arrays.stream(arr).boxed().toList());

        list = list.stream()
                .filter(i -> i>0)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entrySet -> entrySet.getValue()<=k)
                .map(Map.Entry::getKey)
                .distinct()
                .toList();

        int max = list.stream().max(Comparator.naturalOrder()).orElse(-1);
        if(max == -1) {
            return max;
        }
        else {
            return list.stream().filter(i -> i!=max).max(Comparator.naturalOrder()).orElse(-1);
        }
    }

    public static void main(String[] args) {
        SecondLargestUniqueElement secondLargestUniqueElement = new SecondLargestUniqueElement();

        int[] arr = {5, 3, 9, 9, 10, 5, 3, 2};
        int k = 1;
        System.out.println(secondLargestUniqueElement.secondLargestUniqueElement(arr,k));
    }

}


class Solution {
    public int[][] merge(int[][] intervals) {
        for (int[] interval : intervals) {
            System.out.print(interval[0] + " " + interval[1] + " ");
        }
        System.out.println();
        List<int[]> list = new ArrayList<>(Arrays.stream(intervals).toList());
        list = list.stream().sorted(Comparator.comparing(a -> a[0])).collect(Collectors.toList());
        for(int[] a : list)
            System.out.println(a[0] + " " + a[1] + " ");
        for(int i = 0;i<intervals.length;i++)
        {
            intervals[i][0] = list.get(i)[0];
            intervals[i][1] = list.get(i)[1];
        }
        list.clear();
        for(int i = 0;i<intervals.length;i++)
        {
            System.out.print(intervals[i][0]+ " " + intervals[i][1] + " ");
        }
        for(int i = 0;i<intervals.length;i++)
        {
            int start = intervals[i][0];
            while(i < intervals.length-1 && intervals[i][1] >= intervals[i+1][0]) i++;
            list.add(new int[]{start,intervals[i][1]});
        }
        int[][] ans = new int[list.size()][2];
        for(int i = 0;i<ans.length;i++)
        {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }
}


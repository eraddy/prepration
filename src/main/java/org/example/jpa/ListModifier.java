package org.example.jpa;

import java.util.ArrayList;
import java.util.List;

public class ListModifier {
    public List<List<Integer>> oddAndEvenListSeparator(List<Integer> list) {
        if(list == null )
            return null;
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        list.stream()
                .forEach(e -> {
                    if (e % 2 == 0)
                        evenList.add(e);
                    else
                        oddList.add(e);
                }); // could have used the for loop but the question has asked to use the JAVA 8' streams api
        answer.add(oddList);
        answer.add(evenList);
        return answer;
    }
}




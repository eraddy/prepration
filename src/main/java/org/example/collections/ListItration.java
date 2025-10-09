package org.example.collections;

import org.example.dsa.PascalTriangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListItration {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hfa");
        list.add("hfa");
        list.add("hfa");
        list.add("hfa");

        ListIterator<String> stringListIterator = list.listIterator();
        Iterator<String> stringListIterator1 = list.iterator();

        for(int  i=0;i<list.size();i++)
        {
            if(i %2 == 0)
                list.add("aditya");
        }

        System.out.println(list);
    }
}

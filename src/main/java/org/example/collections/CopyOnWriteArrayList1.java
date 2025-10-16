package org.example.collections;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayList1 {
    static void main() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(12);
        list.add(12);
        list.add(12);
        list.add(12);
        for(int i : list)
        {
            if(i == 12)
                list.add(12);
        }
        System.out.println(list);
    }
}

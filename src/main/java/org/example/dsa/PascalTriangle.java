package org.example.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 1;i<=numRows;i++)
        {
            List<Integer> list = new ArrayList<>();
            for(int j = i;j<=i;j++)
            {
                list.add(getSum(i,j));
            }
            ans.add(list);
        }
        return ans;
    }

    public int getSum(int row,int i)
    {
        if(row == 1 || row == 2 || i == 1  || i == row)
        {
            return 1;
        }
        else {
            return 1 + getSum(row-1,i-1);
        }
    }

    public static void main(String[] args) {
        PascalTriangle pascalTriangle = new PascalTriangle();
        System.out.println(pascalTriangle.generate(5));
    }
}

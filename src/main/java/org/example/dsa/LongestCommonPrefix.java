package org.example.dsa;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] str)
    {
        if(str == null || str.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        int k = 0;
        while(true)
        {
            if(k>=str[0].length())
                return sb.toString();
            for(int i = 1;i<str.length;i++)
            {
                if(k>=str[i].length())
                    return sb.toString();
                if(str[i].charAt(k) != str[0].charAt(k))
                {
                    return sb.toString();
                }
            }
            sb.append(str[0].charAt(k));
            k++;
        }
    }
    public static void main(String[] args) {

    }
}

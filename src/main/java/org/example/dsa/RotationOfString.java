package org.example.dsa;

public class RotationOfString {
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length())
            return false;

        if(s.equals(goal))
            return true;

        StringBuilder sb = new StringBuilder(s);

        for(int i=0;i<s.length();i++)
        {
            sb.append(sb.charAt(0));
            sb.delete(0,1);

            if(sb.toString().equals(goal))
                return true;
        }
        return false;
    }
}

package org.example.core;

public class StringClass {
    public static void main(String[] args) {
        String s1 = "adi";
        String s2 = new String("adi").intern();
        String s3 = new String("adi");
//        s3 = s3.intern();
        System.out.println(s1==s2); // as both are pointing to the scp
        System.out.println(s1==s3); // false as the s3 is pointing to heap object
        System.out.println(s1.equals(s2)); // true because of same content

    }
}

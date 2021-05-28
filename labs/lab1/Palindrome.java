package com.lab1;
public class Palindrome {
    public static void main(String[] args) {
        boolean b=true;
        for(int i=0;i<args.length;i++){if(!isPalindrome(args[i])){b=false; break;}}
        System.out.println(b ? "true" : "fase");
        //for(int i=0;i<args.length;i++){System.out.print(args[i]+" ");}
    }
    //переворот строки
    public static String reverseString(String s){String tmp=""; for(int i=s.length();i>0;i--){tmp+=s.charAt(i-1);} return tmp;}
    //проверка на палиндром
    public static boolean isPalindrome(String s){return s.equals(reverseString(s));}
}

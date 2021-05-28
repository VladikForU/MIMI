package com.lab7;

public class Main {
    public static void  main(String[] args){
        if(args.length<2){System.out.print("too few arguments");}
        else{
            URLPool pool=new URLPool(args[0],Integer.decode(args[1]));
            pool.run();
        }
    }
}

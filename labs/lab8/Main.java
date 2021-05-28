package com.lab8;

public class Main {
    public static void  main(String[] args){
        if(args.length<3){System.out.print("too few arguments");}
        else{
            URLPool pool=new URLPool(args[0],Integer.decode(args[1]),Integer.decode(args[2]));
            pool.run();
        }
    }
}

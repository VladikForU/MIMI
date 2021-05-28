package com.lab7;

public class URL_Info {
    public final String url;
    public final int searchDepth;
    private String tabs="";
    public URL_Info(String url){this(url,0);}
    public URL_Info(String url,  int searchDepth){
        this.url=url; this.searchDepth=searchDepth;
        for(int i=0;i<searchDepth+1;i++){this.tabs+="\t";}
    }
    public static String urlProtocol(String url){int tmp=url.indexOf("://"); return tmp>=0 ? url.substring(0,tmp) : null;}

    public static String urlHost(String url){
        String protocol=urlProtocol(url);
        if(protocol!=null){int tmp=url.indexOf('/',protocol.length()+3);
        return url.substring(protocol.length()+3,tmp!=-1 ? tmp : url.length());}
    return null;}

    public String toString(int size){return tabs+size+" "+url;}
    public String toString(){return searchDepth+tabs+url;}
    public String toString(String color){return color+searchDepth+URLPool.ANSI_RESET+tabs+url;}
    public String toString(String cDepth,String cURL){return cDepth+searchDepth+URLPool.ANSI_RESET+tabs+cURL+url+URLPool.ANSI_RESET;}
    public void print(){System.out.print(toString());}
    public void println(){System.out.println(toString());}
    public void println(String color){System.out.println(toString(color));}
    public void println(String cDepth,String cURL){System.out.println(toString(cDepth, cURL));}
}

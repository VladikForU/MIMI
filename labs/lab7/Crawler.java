package com.lab7;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

public class Crawler {

    public Crawler(){}
    public LinkedList<URL_Info> scan(URL_Info url_info){return searchURLs(url_info);}

    private String loadPage(URL_Info urlInfo){
        String page="",tmp,host=URL_Info.urlHost(urlInfo.url);
        if(host==null){return null;}
        Socket socket;
        try{
            if(URL_Info.urlProtocol(urlInfo.url).toLowerCase().equals("http")){socket=new Socket(host, 80);}
            else{socket=SSLSocketFactory.getDefault().createSocket(host,443);}
            socket.setSoTimeout(1000);
            PrintWriter pw=new PrintWriter(socket.getOutputStream());
            pw.print("GET "+ urlInfo.url+" HTTP/1.0\r\n\r\n");
            pw.flush();
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while((tmp=reader.readLine())!=null){page+=tmp+"\n";}
            pw.close();
            reader.close();
            socket.close();
        }catch(Exception e){
            //e.printStackTrace();
        }
    return page;}

    public LinkedList<URL_Info> searchURLs(URL_Info urlInfo){
        String page=loadPage(urlInfo);
        LinkedList<URL_Info> list=new LinkedList<>();
        if(page==null || page.length()==0){return null;}
        int start,end,newSearchDepth=urlInfo.searchDepth+1;
        start=page.indexOf("href=\"http");
        while(start>=0){
            start+=6;
            end=page.indexOf('"',start);
            if(end>=0){list.add(new URL_Info(page.substring(start,end),newSearchDepth));}
            start=page.indexOf("href=\"http",end+1);
        }
    return list;}

    public static void printURLs(LinkedList<URL_Info> list){
        Iterator<URL_Info> iterator=list.listIterator();
        while(iterator.hasNext()){System.out.println(iterator.next().toString());}
        System.out.println(URLPool.ANSI_RED+"list_size="+list.size()+URLPool.ANSI_RESET);
    }
}

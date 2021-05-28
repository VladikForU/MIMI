package com.lab8;

import com.lab7.Crawler;
import com.lab7.URL_Info;

import java.util.Iterator;
import java.util.LinkedList;

public class URLPool{
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    int maxSearchDepth;
    int d;
    int threadcount;
    LinkedList<URL_Info> checked=new LinkedList<>(),unchecked=new LinkedList<>(),checking=new LinkedList<>(),found=new LinkedList<>();

    public URLPool(String url,int maxSearchDepth,int threadcount){
        this.maxSearchDepth=maxSearchDepth; this.threadcount=threadcount>0 ? threadcount : 1;
        checking.add(new URL_Info(url,0));
    }
    public synchronized void addfound(LinkedList<URL_Info> list){found.addAll(list);}
    //public void addchecking(LinkedList<URL_Info> list){if(list!=null){checking.addAll(list);}}

    public synchronized void addchecked(URL_Info url_info){checked.add(url_info);}
    public synchronized void addunchecked(URL_Info url_info){unchecked.add(url_info);}
    public void printchecked(){System.out.println("\nprintchecked():"); Crawler.printURLs(checked);}
    public void printunchecked(){System.out.println("\nprintunchecked():"); Crawler.printURLs(unchecked);}

    public class RunnubleTask implements Runnable{
        Crawler crawler;
        URL_Info task;
        String threadname="";
        public RunnubleTask(Crawler crawler){this.crawler=crawler;}
        public RunnubleTask(Crawler crawler, URL_Info task){this(crawler,task,null);}
        public RunnubleTask(Crawler crawler, URL_Info task,String threadname){this(crawler); setPrefix(threadname); if(task!=null){setTask(task);}}
        public void setTask(URL_Info task){this.task=task;}
        public void setPrefix(String threadname){if(threadname!=null){this.threadname=threadname;}}
        public void setTaskAndName(URL_Info task,String threadname){this.task=task; if(threadname!=null){this.threadname=threadname;}}
        public void run(){
            LinkedList<URL_Info> list=crawler.scan(task);
            if(list==null){
                addunchecked(task);
                task.println(threadname+" "+ANSI_RED,ANSI_RED_BACKGROUND);
            }
            else{
                addchecked(task);
                addfound(list);
                task.println(threadname+" "+ANSI_GREEN);
            }
            task=null;
        }
    }

    public Thread[] threads;
    public RunnubleTask[] runnubleTasks;
    public Crawler[] crawlers;

    public void waitThreads(){try{for(int i=0;i<threads.length;i++){if(threads[i]!=null){threads[i].join();}}}catch(InterruptedException e){e.printStackTrace();}}
    public void run(){
        Thread thread=new Thread(){
            @Override
            public void run(){
                super.run();
                long startTime=System.currentTimeMillis();
                d=0;
                threads=new Thread[threadcount];
                runnubleTasks=new RunnubleTask[threads.length];
                crawlers=new Crawler[threads.length];
                for(int i=0;i<crawlers.length;i++){crawlers[i]=new Crawler(); runnubleTasks[i]=new RunnubleTask(crawlers[i]);}
                Iterator<URL_Info> iter=checking.iterator();
                URL_Info last=checking.getLast(),tmp=null;
                int taskcount=checking.size(),numtask=0;
                while(d<maxSearchDepth || unchecked.size()>0){
                    for(int i = 0; i< threads.length && tmp!=last; i++){
                        if(threads[i]==null || threads[i].getState()==State.TERMINATED){
                            tmp=iter.next();
                            System.out.print("\r"+(d+1)+"/"+maxSearchDepth+" | "+(++numtask)+"/"+taskcount+" | "+(System.currentTimeMillis()-startTime)/1000 + "sec");
                            runnubleTasks[i].setTaskAndName(tmp,"\rT-"+(i<10?"0"+i:i));
                            threads[i]=new Thread(runnubleTasks[i]);
                            threads[i].start();
                        }
                    }
                    if(tmp==last){
                        waitThreads();
                        checking=found;
                        found=new LinkedList<>();
                        iter=checking.iterator();
                        last=checking.getLast();
                        tmp=null;
                        numtask=0; taskcount=checking.size();
                        d++;
                        if(d>=maxSearchDepth){
                            checked.addAll(checking);
                            checking=unchecked;
                            unchecked=new LinkedList<>();
                        }
                    }
                }
                checked.addAll(checking);
                printchecked();
                printunchecked();
                long endTime = System.currentTimeMillis();
                System.out.println(ANSI_RED + "finish d="+d + ANSI_RESET);
                System.out.println("Total execution time: " + (endTime-startTime)/(float)1000 + "sec");
            }
        };
        thread.start();
        System.out.println(ANSI_GREEN + "main thread finish"+ ANSI_RESET);
    }
}

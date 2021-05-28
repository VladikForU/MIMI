package tasks.level6;

import java.util.HashMap;


public class Main {
    public static void main(String args[]){
        System.out.println(bell(3));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println(validColor("rgba(255,0,255,0.123)"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2",new String[]{"b"}));
        tasks.level5.Main.printStrArr(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(ulam(206));
        System.out.println(longestNonrepeatingSubstring("abcafcbb"));
        System.out.println(convertToRoman(2987));
        System.out.println(formula("16 * 10 = 160 = 14 + 120")); //
        System.out.println(palindromedescendant("23336014"));
    }
    public static int bell(int n){
        int[] up=new int[n],down=new int[n];
        up[0]=1;
        int k=1;
        for(int i=1;i<n;i++){
            down[0]=up[k-1];
            for(int j=0;j<k;j++){down[j+1]=down[j]+up[j];}
            k++;
            for(int j=0;j<k;j++){up[j]=down[j];}
        }
    return up[up.length-1];}
    public static String translateWord(String word){
        if(word.length()==0){return "";} String translatedWord;
        char[] c=new char[]{'a','e','i','o','y','u'};
        int e=0; boolean b=false,upcase=false;
        for(int i=0;i<word.length();i++){
            char cw=word.charAt(i);
            for(int j=0;j<c.length;j++){if(cw==c[j]){b=true; break;}}
            if(b){e=i; break;}
        }
        upcase=word.charAt(0)<'a';
        String tmp=word.toLowerCase();
        if(e>0){
            translatedWord=(upcase? tmp.substring(e,e+1).toUpperCase()+tmp.substring(e+1) : tmp.substring(e))+tmp.substring(0,e)+"ay";
        }else{
            translatedWord=(upcase ? tmp.substring(0,1).toUpperCase()+tmp.substring(1) : tmp)+"yay";
        }
    return translatedWord;}
    public static String translateSentence(String sentence){
    String translated=""; int s=0,e=0;
        for(int i=0;i<sentence.length();i++){
            if(sentence.charAt(i)==' ' || sentence.length()-1==i){
                e=i;
                translated+=translateWord(sentence.substring(s,e))+sentence.charAt(i);
                s=e+1;
            }
        }
    return translated;}

    public static boolean validColor(String color){
        String format=""; int s=0;
        try{
            for(int i=0;i<color.length();i++){if(color.charAt(i)=='('){s=i;format=color.substring(0,i); break;}}
            color=color.substring(s+1,color.length()-1);
            int[] rgb=new int[3]; float a;
            for(int i=0;i<3;i++){
                s=color.indexOf(',');
                if(s==-1){s=color.length();}
                if(s>0){rgb[i]=Integer.parseInt(color.substring(0,s));}else{return false;}
                color=color.substring(Math.min(s+1,color.length()));
                if(rgb[i]>255 || rgb[i]<0){return false;}
            }
            if(format.equals("rgb") && color.length()==0){return true;}
            a=Float.parseFloat(color);
            if(a>1 || a<0){return false;}
        }catch(Exception e){return false;}
    return true;}

    public static String stripUrlParams(String url){return stripUrlParams(url,null);}
    public static String stripUrlParams(String url,String[] delparams){
        int s=url.indexOf('?'),e=0,value;
        if(s==-1){return url;}
        String newurl=url.substring(0,s+1),params=url.substring(s+1),param;
        HashMap<String,Integer> map=new HashMap<>();
        s=0;
        for(int i=0;i<params.length();i++){
            if(params.charAt(i)=='='){
                e=i;
                param=params.substring(s,e);
                s=e+1;
                e=params.indexOf('&',s);
                String tmp=params.substring(s,(e==-1? params.length():e));
                value=Integer.parseInt(tmp);
                map.put(param,value);
                s=e+1;
            }
        }
        if(delparams!=null){for(int i=0;i<delparams.length;i++){map.remove(delparams[i]);}}
        String[] keys=map.keySet().toArray(new String[0]);
        Integer[] values=map.values().toArray(new Integer[0]);
        for(int i=0;i<map.size();i++){newurl+=keys[i]+"="+values[i]+"&";}
        newurl=newurl.substring(0,newurl.length()-1);
    return newurl;}

    public static String[] getHashTags(String title){
        int s=0,e=0,min,mini; String[] words=new String[]{"","",""}; String word;
        for(int i=0;i<title.length();i++){
            char c=title.charAt(i);
            if(c==' ' || c==',' || c=='.' || title.length()-1==i){
                e=i;
                word="#"+title.substring(s,e).toLowerCase();
                min=words[0].length(); mini=0;
                for(int j=1;j<3;j++){if(words[j].length()<min){min=words[j].length(); mini=j;}}
                if(word.length()>words[mini].length()){words[mini]=word;}
                s=e+1;
            }
        }
        for(int j=0;j<2;j++)
        for(int i=0;i<2;i++){
            if(words[i].length()<words[i+1].length()){
                word=words[i+1]; words[i+1]=words[i]; words[i]=word;
            }
        }
    return words;}

    public static int ulam(int n){
        int[] ulams=new int[n]; int k;
        for(int i=0;i<ulams.length;i++){ulams[i]=i+1;}
        for(int x=4;x<ulams.length;x++){
            k=0;
            label: for(int i=0;i<x-1;i++){
                for(int j=i+1;j<x;j++){
                    if(ulams[i]+ulams[j]==ulams[x]){k++; if(k==2){break label;}}
                }
            }
            if(k!=1){
                for(int y=x;y<ulams.length-1;y++){ulams[y]=ulams[y+1];}
                ulams[ulams.length-1]++;
                x--;
            }
        }
    return ulams[ulams.length-1];}


    public static String longestNonrepeatingSubstring(String str){
        String tmp,maxstr="";
        for(int j=0;j<str.length();j++){
            tmp=str.charAt(j)+"";
            for(int i=1;i<str.length()-1;i++){
                if(tmp.contains(str.charAt(i)+"")){
                    if(maxstr.length()<tmp.length()){maxstr=tmp; break;}
                }else{tmp+=str.charAt(i);}
            }
        }
    return maxstr;}

    public static String convertToRoman(int n){
        int M=n/1000;
        n%=1000;
        int s=n/100;
        n%=100;
        int d=n/10;
        int e=n%10;
        String num="";
        num+=repchar('M',M);
        switch(s){
            case 0: break;
            case 1:
            case 2:
            case 3: num+=repchar('C',s); break;
            case 4: num+="CD";break;
            case 5:
            case 6:
            case 7:
            case 8: num+="D"+repchar('C',s-5); break;
            case 9: num+="CM";
        }
        switch(d){
            case 0: break;
            case 1:
            case 2:
            case 3: num+=repchar('X',d); break;
            case 4: num+="XL";break;
            case 5:
            case 6:
            case 7:
            case 8: num+="L"+repchar('X',d-5); break;
            case 9: num+="XC";
        }
        switch(e){
            case 0: break;
            case 1:
            case 2:
            case 3: num+=repchar('I',e); break;
            case 4: num+="IV";break;
            case 5:
            case 6:
            case 7:
            case 8: num+="V"+repchar('I',e-5); break;
            case 9: num+="IX";
        }
    return num;}
    public static String repchar(char c,int k){String str="";for(int i=0;i<k;i++){str+=c;}return str;}


    public static boolean formula(String str){
        int s=0,e; String tmp,tmp2=""; int a=-9999,b,r=-9999,k=0;
        for(int i=0;i<=str.length();i++){
            if(str.length()==i || str.charAt(i)==' '){
                e=i;
                tmp=str.substring(s,e);
                k++;
                if(a==-9999){a=Integer.parseInt(tmp); k--;}
                else{
                    if(tmp.charAt(0)=='='){
                        if(r==-9999){r=a;}
                        else{if(r!=a){return false;}}
                        a=-9999;
                        k--;
                    }
                    else{
                        if(k%2==1){tmp2=tmp;}
                        else{
                            b=Integer.parseInt(tmp);
                            switch(tmp2.charAt(0)){
                                case '+': a+=b; break;
                                case '-': a-=b;break;
                                case '*': a*=b;break;
                                case '/': a/=b;break;
                            }
                        }
                    }
                }
                s=e+1;
            }
        }
    return r==a;}

    public static boolean palindromedescendant(String str){
        if(isPalindrome(str)){return true;}
        else{String tmp=""; int len=str.length();
            for(int i=0;i<len;i+=2){tmp+=(str.charAt(i)-48+str.charAt(i+1)-48);}
        return tmp.length()>2? palindromedescendant(tmp) : isPalindrome(tmp);}
    }
    public static boolean isPalindrome(String str){
        int len=str.length(); if(len==1){return true;}
        String str1=str.substring(0,len/2),tmp=str.substring(len/2+len%2),str2="";
        len=tmp.length();
        for(int i=1;i<=len;i++){str2+=tmp.charAt(len-i);}
    return str1.equals(str2);}
}

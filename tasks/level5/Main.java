package tasks.level5;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String args[]){
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(sumDigProd(new int[]{1,2,3,4,5,6}));
        printStrArr(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"}));
        System.out.println(validateCard("1234567890123452"));
        System.out.println(numToEng(126));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(hexLattice(37));
    }
    public static int[] encrypt(String str){int[] arr=new int[str.length()]; arr[0]=str.charAt(0); for(int i=1;i<arr.length;i++){arr[i]=(str.charAt(i)-str.charAt(i-1));} return arr;}
    public static String decrypt(int[] arr){String str=(char)arr[0]+""; for(int i=1;i<arr.length;i++){str+=(char)(str.charAt(i-1)+arr[i]);} return str;}
    public static boolean canMove(String figure,String pos,String des){
        int dx=des.toUpperCase().charAt(0)-pos.toUpperCase().charAt(0),dy=des.charAt(1)-pos.charAt(1);
        boolean r=false;
        switch(figure){
            case "Pawn": r=(dy==0 && dx==1); break;
            case "Rook": r=(dx==0 || dy==0); break;
            case "Horse": r=((Math.abs(dx)==2 && Math.abs(dy)==1) ||(Math.abs(dy)==2 && Math.abs(dx)==1));break;
            case "Bishop": r=(Math.abs(dx)==Math.abs(dy)); break;
            case "Queen": r=((Math.abs(dx)==Math.abs(dy)) || (dx==0 || dy==0)); break;
            case "King": r=(((Math.abs(dx)==Math.abs(dy)) || (dx==0 || dy==0)) && dx<2 && dy<2); break;
        }
    return r;}
    public static boolean canComplete(String a,String b){
        String rex=""; for(int i=0;i<a.length();i++){rex+=a.charAt(i)+".*";}
        return Pattern.compile(rex,Pattern.CASE_INSENSITIVE).matcher(b).find();
    }
    public static int sumDigProd(int a){int b=1; while(a>1){b*=a%10; a/=10;} return b>9 ? sumDigProd(b) : b;}
    public static int sumDigProd(int a,int b){return sumDigProd(a+b);}
    public static int sumDigProd(int[] ar){int a=0; for(int i=0;i<ar.length;i++){a+=ar[i];}  return sumDigProd(a);}

    public static String[] sameVowelGroup(String[] arr){
        char[] c=new char[]{'a','e','i','o','y','u'},c2;
        int g=0,t=0;
        ArrayList<String> list=new ArrayList<>();
        for(int i=0;i<c.length;i++){if(arr[0].contains(c[i]+"")){g++;}}
        c2=new char[g];
        for(int i=0;i<c.length;i++){if(arr[0].contains(c[i]+"")){c2[t]=c[i]; t++;}}
        for(int i=0;i<arr.length;i++){
            t=0; for(int j=0;j<c2.length;j++){if(arr[i].contains(c2[j]+"")){t++;}}
            if(t==g){list.add(arr[i]);}
        }
    return list.toArray(new String[0]);}
    public static void printStrArr(String[] arr){for(int i=0;i<arr.length;i++){System.out.print(arr[i]+"  ");} System.out.println();}

    public static boolean validateCard(String num){
        if(num.length()<14 || num.length()>19){return false;}
        int digit=num.charAt(num.length()-1)-48,d,sum=0;
        num=num.substring(0,num.length()-1);
        int[] arr=new int[num.length()];
        for(int i=0;i<arr.length;i++){
            d=num.charAt(arr.length-i-1)-48;
            arr[i]=(i%2==0 ? (d/5 + d*2%10): d);
            sum+=arr[i];
        }
    return (digit==10-sum%10);}

    public static String numToEng(int n){
        String s=""; int d=100;
        if(n==0){return "zero";}
        while(n>0){
        switch(d==10? n/d*10 : n/d){
            case 1: s+="one "; break;
            case 2: s+="two "; break;
            case 3: s+="three "; break;
            case 4: s+="four "; break;
            case 5: s+="five "; break;
            case 6: s+="six "; break;
            case 7: s+="seven "; break;
            case 8: s+="eight "; break;
            case 9: s+="nine "; break;
            case 10: s+="ten "; break;
            case 11: s+="eleven "; break;
            case 12: s+="twelve "; break;
            case 13: s+="thirteen "; break;
            case 14: s+="fourteen "; break;
            case 15: s+="fifteen "; break;
            case 16: s+="sixteen "; break;
            case 17: s+="seventeen "; break;
            case 18: s+="eighteen "; break;
            case 19: s+="nineteen "; break;
            case 20: s+="twenty "; break;
            case 30: s+="thirty "; break;
            case 40: s+="forty "; break;
            case 50: s+="fifty "; break;
            case 60: s+="sixty "; break;
            case 70: s+="seventy "; break;
            case 80: s+="eighty "; break;
            case 90: s+="ninety "; break;
        }
        if(n>99){s+="hundred ";}
        n%=d; d/=10; if(n<20){d=1;}
        }
    return s;}

    public static String getSha256Hash(String data){
        StringBuffer sb=new StringBuffer();
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            byte byteData[]=md.digest();
            for(int i=0; i<byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
        }catch(Exception e){e.printStackTrace();}
        return sb.toString();
    }
    public static String correctTitle(String str){
        str=str.toLowerCase(); int s=0,e;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' ' || str.length()-1==i){
                e=i;
                switch(str.substring(s,e)){
                    case "of":
                    case "the":
                    case "in":
                    case "and" : break;
                    default: str=str.replace(str.substring(s,e),str.substring(s,s+1).toUpperCase()+str.substring(s+1,e)); break;
                }
                s=e+1;
            }
        }
    return str;}

    public static String hexLattice(int n){
        String lat="";
        int k=1; int t=1;
        while(t<n){t+=6*k; k++;}
        if(t!=n){return "Invalid";}
        t=0;
        for(int i=k;i>0;i--){
            for(int j=0;j<i;j++){lat+=" ";}
            for(int j=0;j<k+t;j++){lat+="o ";}
            for(int j=0;j<i;j++){lat+=" ";}
            lat+="\n"; t++;
        }
        t-=2;
        for(int i=2;i<k+1;i++){
            for(int j=0;j<i;j++){lat+=" ";}
            for(int j=0;j<k+t;j++){lat+="o ";}
            for(int j=0;j<i;j++){lat+=" ";}
            lat+="\n"; t--;
        }
    return lat.substring(0,lat.length()-1);}
}

package tasks.level4;

public class Main {
    public static void main(String args[]){
        System.out.println(formate(10,7,"hello my name is Bessie and this is my essay"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("isModalOpen"));
        System.out.println(overTime(new float[]{13.25f, 15, 30, 1.5f}));
        System.out.println(BMI("55 kilos", "1.65 meters"));
        System.out.println(bugger(999));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and hAm."));
        System.out.println(trouble(451999277, 477722899));
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
    }
    public static String formate(int n,int k,String str){
        String f="",word,line=""; int s=0,e=0,p=0;
        for(int i=0;i<n && e!=-1;i++){
            e=str.indexOf(' ',s);
            word=str.substring(s,e==-1 ? str.length() : e);
            if(line.length()-p+word.length()<=k){line+=word+" ";p++;}
            else{f+=line.substring(0,line.length()-1)+"\n"; line=word+" "; p=1;}
            s=e+1;
        }
    return f+line;}
    public static String split(String str){
        String res="["; int depth=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='('){if(depth==0){res+="\"";}depth++;res+="(";}
            else{depth--;res+=")";if(depth==0){res+="\",";}}
        }
    return res.substring(0,res.length()-1)+"]";}
    public static String toCamelCase(String str){
        String st="";
        int s=str.indexOf('_')+1,i=0;
        while(s>0){
            st+=str.substring(st.length()+i,s-1)+(char)(str.charAt(s)-32);
            s=str.indexOf('_',s)+1;
            i++;
        }
    return st+str.substring(st.length()+i);}
    public static String toSnakeCase(String str){String st="";for(int i=0;i<str.length();i++){char c=str.charAt(i);st+=('A'<=c && c<='Z') ? "_"+(char)(c+32) : c;}return st;}

    public static String overTime(float[] a){return "$"+((Math.min(17,a[1])-a[0])+a[3]*(Math.max(17,a[1])-17))*a[2];}

    public static String BMI(String mass,String height){
        float m=Float.parseFloat(mass.substring(0,mass.indexOf(' '))),h=Float.parseFloat(height.substring(0,height.indexOf(' ')));
        if(mass.charAt(mass.indexOf(' ')+1)=='p'){m*=0.453592f;}
        if(height.charAt(height.indexOf(' ')+1)=='i'){h*=0.0254f;}
        m=m/(h*h);
    return String.format("%.1f",m)+(m<18.5f ? " Underweight" : (m>24.9f ? " Overweight" : " Normal weight"));}
    public static int bugger(int a){
        int k=0,m;
        while(a>9){
            m=1;
            while(a>0){m*=a%10; a/=10;}
            a=m;
            k++;
        }
    return k;}
    public static String toStarShorthand(String str){String res=""; for(int i=0;i<str.length();i++){char c=str.charAt(i); int rep=1; res+=c;int j;for(j=i+1;j<str.length() && str.charAt(j)==c;j++){rep++;}if(rep>1){res+="*"+rep;} i=j-1;} return res;}
    public static boolean doesRhyme(String str1,String str2){
        str1=str1.substring(str1.lastIndexOf(' ')).toLowerCase();
        str2=str2.substring(str2.lastIndexOf(' ')).toLowerCase();
        char[] c=new char[]{'a','e','i','o','y','u'};
        for(int i=0;i<c.length;i++){if((str1.contains(c[i]+"") ^ str2.contains(c[i]+""))){return false;}}
    return true;}
    public static boolean trouble(long l1,long l2){
        long t1,t2,t3=l2;
        while(l1>0){
            while(l1%10!=l1%1000/100 || l1%10!=l1%100/10){l1/=10;}
            t1=l1%10;
            l2=t3;
            while(l2>0){
                while(l2%10!=l2%100/10){l2/=10;}
                t2=l2%10;
                if(t1==t2){return true;}
            }
        }
    return false;}
    public static int countUniqueBooks(String str,char c){
        int un=0; String tmp;
        int s=str.indexOf(c)+1,e;
        while(s>0){
            e=str.indexOf(c,s);
            tmp=str.substring(s,e);
            while(tmp.length()>0){un++; tmp=tmp.replace(tmp.charAt(0)+"","");}
            s=str.indexOf(c,++e)+1;
        }
    return un;}
}

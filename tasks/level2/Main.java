package tasks.level2;

public class Main {
    public static void main(String args[]){
        System.out.println(repeat("mice",5));
        System.out.println(differenceMaxMin(new int[]{10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[]{1, 2, 3, 4}));
        System.out.println(arrtoString(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3})));
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(Fibonacci(12));
        System.out.println(isValid("59001"));
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(boxSeq(5));
    }
    public static String repeat(String str,int n){String result=""; for(int i=0;i<str.length();i++){char c=str.charAt(i);for(int j=0;j<n;j++){result+=c;}}return result;}
    public static int differenceMaxMin(int[] arr){int max=arr[0],min=arr[0];for(int i=0;i<arr.length;i++){if(arr[i]>max){max=arr[i];}if(arr[i]<min){min=arr[i];}}return max-min;}
    public static boolean isAvgWhole(int[] arr){int sum=0; for(int i=0;i<arr.length;i++){sum+=arr[i];} return sum%arr.length==0;}
    public static int[] cumulativeSum(int[] arr){int[] arr2=new int[arr.length]; for(int i=0;i<arr2.length;i++){int sum=0;for(int j=0;j<=i;j++){sum+=arr[j];}arr2[i]=sum;} return arr2;}
    public static int getDecimalPlaces(String str){int in=str.indexOf('.'); return in<0 ? 0 : str.substring(in+1).length();}
    public static int Fibonacci(int f){if(f<3){return f<2 ? 1: 2;}int n1=0,n2=1,n3=1; for(int i=0;i<f;i++){n3=n1+n2; n1=n2; n2=n3;} return n3;}
    public static boolean isValid(String str){if(str.length()==5){for(int i=0;i<5;i++){if(str.charAt(i)<'0' || str.charAt(i)>'9'){return false;}}return true;}return false;}
    public static boolean isStrangePair(String str1,String str2){if(str1.length()==0 || str2.length()==0){return true;} return str1.charAt(0)==str2.charAt(str2.length()-1) && str2.charAt(0)==str1.charAt(str1.length()-1);}
    public static boolean isPrefix(String word,String prefix){return word.startsWith(prefix.replace("-",""));}
    public static boolean isSuffix(String word,String suffix){return word.endsWith(suffix.replace("-",""));}
    public static int boxSeq(int a){return a%2*2+a;}
    public static String arrtoString(int[] arr){String tmp=""; for(int i=0;i<arr.length;i++){tmp+=arr[i]+" ";} return tmp;}
}

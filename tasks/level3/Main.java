package tasks.level3;

public class Main {
    public static void main(String args[]){
        System.out.println(solutions(1, 0, -1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(496));
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(isValidHexCode("#EA6CE6"));
        System.out.println(same(new int[]{9, 8, 7, 6},new int[]{4, 4, 3,1,6}));
        System.out.println(isKaprekar(703));
        System.out.println(longestZero("01100001011000"));
        System.out.println(nextPrime(98));
        System.out.println(rightTriangle(145, 105, 100));
    }
    public static int solutions(int a,int b,int c){int d=b*b-4*a*c; return (d>0 ? 2 : (d<0 ? 0 : 1));}
    public static int findZip(String str){int s=str.indexOf("zip");  return s==-1 ? s : str.indexOf("zip",s+1);}
    public static boolean checkPerfect(int a){int sum=1; for(int i=2;i<=a/2;i++){if(a%i==0){sum+=i;}}return sum==a;}
    public static String flipEndChars(String str){if(str.length()<2){return "Incompatible.";} if(str.charAt(0)==str.charAt(str.length()-1)){return "Two's a pair.";}else{return str.charAt(str.length()-1)+str.substring(1,str.length()-1)+str.charAt(0);}}
    public static boolean isValidHexCode(String str){if(str.charAt(0)=='#'){for(int i=1;i<7;i++){char c=str.charAt(i);if(!(('0'<=c && c<='9')||('a'<=c && c<='f')||('A'<=c && c<='F'))){return false;}}return true;}return false;}
    public static boolean same(int[] a1,int[] a2){int b1=a1.length,b2=a2.length; for(int i=0;i<a1.length;i++){if(a1[i]!=-9999){for(int j=i+1;j<a1.length;j++){if(a1[i]==a1[j]){a1[j]=-9999; b1--;}}}} for(int i=0;i<a2.length;i++){if(a2[i]!=-9999){for(int j=i+1;j<a2.length;j++){if(a2[i]==a2[j]){a2[j]=-9999; b2--;}}}} return b1==b2;}
    public static boolean isKaprekar(int n){int kv=n*n,d=1,d2=1,i;for(i=0;kv/d>0;i++){d*=10;if(i%2==0){d2*=10;}}return kv/d2+kv%d2==n;}
    public static String longestZero(String str){
        int max=0,tmp;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='0'){
                int j; tmp=1;
                for(j=i+1;j<str.length() && str.charAt(j)=='0';j++){tmp++;}
                if(tmp>max){max=tmp;}
                i=j;
            }
        }
        String ts="";
        for(int i=0;i<max;i++){ts+='0';}
    return ts;}
    public static int nextPrime(int n){
        int[] a=new int[n*2];
        for(int i=0;i<a.length;i++){a[i]=i;}
        for(int i=2;i<=n/2;i++){if(a[i]!=-1){for(int j=i+1;j<a.length;j++){if(a[j]%a[i]==0){a[j]=-1;}}}}
        if(a[n]!=-1){return n;}
        for(int i=n+1;i<a.length;i++){if(a[i]!=-1){return a[i];}}
    return 0;}
    public static boolean rightTriangle(int a,int b,int c){int x=a*a,y=b*b,z=c*c; return (x+y==z || x+z==y || y+z==x);}

}

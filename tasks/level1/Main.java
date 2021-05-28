package tasks.level1;

public class Main {
    public static void main(String args[]){
        System.out.println(remainder(-9,45));
        System.out.println(triArea(7, 4));
        System.out.println(animals(5, 2, 8));
        System.out.println(profitableGamble(0.2f, 50, 9));
        System.out.println(operation(24, 26, 2));
        System.out.println(ctoa('['));
        System.out.println(addUpTo(10));
        System.out.println(nextEdge(9,2));
        System.out.println(sumOfCubes(new int[]{3, 4, 5}));
        System.out.println(abcmath(42, 5, 10));
    }
    public static int remainder(int a,int b){return a%b;}
    public static double triArea(double h,double c){return h*c/2;}
    public static int animals(int a,int b,int c){return 2*a+(b+c)*4;}
    public static boolean profitableGamble(float prob,float prize,float pay){return (prob*prize>pay);}
    public static String operation(int n,int a,int b){
        if(n==a+b){return "\"added\"";}
        if(n==a-b || n==b-a){return "\"subtracted\"";}
        if(n==a*b){return "\"mul\"";}
        if(n==a/b || n==b/a){return "\"divided\"";}
    return "\"none\"";}
    public static int ctoa(char c){return c;}
    public static int addUpTo(int a){int tmp=0;for(int i=0;i<=a;i++){tmp+=i;} return tmp;}
    public static int nextEdge(int a,int b){return a+b-1;}
    public static int sumOfCubes(int[] arr){int tmp=0;for(int i=0;i<arr.length;i++){tmp+=arr[i]*arr[i]*arr[i];}return tmp;}
    public static boolean abcmath(int a,int b,int c){for(int i=0;i<b;i++){a+=a;}return (a%c==0);}
}

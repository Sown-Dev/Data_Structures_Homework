package HW2;

public class Test {
    static int iterations=0;
    public static void main(String args[]){
        int n=10000;

        Test test= new Test();
        test.foo(n,2);


        System.out.println(iterations);
    }
    public int foo(int n, int k) {
        iterations++;
        if(n<=k)
            return 1;
        else
            return foo(n/k,k) + 1;
    }
}

package HW2;

public class Problem3 {
    public static void main(String args[]){
        int n=5;
        BigO methods= new BigO();

        long startTime1 = System.nanoTime();
        methods.cubic(n);
        long endTime1 = System.nanoTime();

        long startTime2 = System.nanoTime();
        methods.exp(n);
        long endTime2 = System.nanoTime();

        long startTime3 = System.nanoTime();
        methods.constant(n);
        long endTime3 = System.nanoTime();

        double elapsed1 = (endTime1-startTime1)/1000;
        double elapsed2 = (endTime2-startTime2)/1000;
        double elapsed3 = (endTime3-startTime3)/1000;
        System.out.println("\n\nElapsed Time:\nCubic took "+elapsed1+"ms\nExponential took "+ elapsed2 + "ms\nConstant took "+elapsed3+"ms");
        System.out.println("n="+n+" cubic: " +elapsed1+"ms exp: "+ elapsed2 + "ms const: "+elapsed3+"ms");
    }
}

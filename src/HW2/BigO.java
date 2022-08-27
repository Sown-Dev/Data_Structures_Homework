package HW2;

public class BigO implements BigOInterface{


    @Override
    public void cubic(int n) {
        int iterations=0;
        //I know this could be done in a simpler way, but this is how you would implement this for a practical use.
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    iterations++;
                }
            }
        }
        System.out.println("cubic took: "+iterations+" iterations with n of "+n);
    }

    @Override
    public void exp(int n) {
        int iterations=0;
        for(int i=0;i<(Math.pow(2,n));i++){
            iterations++;
        }
        System.out.println("exponential took: "+iterations+" iterations with n of "+n);
    }

    @Override
    public void constant(int n) {
        System.out.println("bruh");
    }


}
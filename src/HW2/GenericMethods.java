package HW2;

public class GenericMethods implements GenericMethodsInterface {

    @Override
    public  <AnyType extends Comparable<AnyType>> int binarySearch(AnyType[] a, AnyType x) {
        return bSearch(a,x, 0, a.length-1);
    }

    @Override
    public  <AnyType extends Comparable<AnyType>> int linearSearch(AnyType[] a, AnyType x) {
        for(int i =0; i<a.length; i++){
            if(a[i].compareTo(x)==0){
                return i;
            }
        }
        return -1;
    }

    private static <AnyType extends Comparable<AnyType>> int bSearch(AnyType[] a, AnyType x, int low, int max){
        //print progress:
        System.out.println("\nArray w/ markers:");
        for(int i =0; i<a.length;i++){
            if(i==low){
                System.out.print("  <|>  ");
            }

            System.out.print(a[i]);
            if(i==max){
                System.out.print("  <|>  ");
            }
        }


        int index=((max+low)/2); //center of the array
        System.out.println("\nIndex:" +index + " max:"+max + " low:"+low);
        if(low<max) {
            if (a[index].compareTo(x) == 0) {
                return index;
            }
            if (a[index].compareTo(x) >0) {
                //less than
                return bSearch(a,x, low, index);
            }
            if (a[index].compareTo(x) < 0) {
                //greater than
                return bSearch(a,x, index+1, max);
            }
        }
        return -1;
    }

    public static void main(String args[]){
        //testing
        Rectangle[] rArray = new Rectangle[20];
        for(int i=0; i<rArray.length;i++){
            Rectangle rect = new Rectangle(i*2,i);
            System.out.print(rect);
            rArray[i]=rect;
        }
        System.out.println("\n\n-Starting Search-\n");

        GenericMethods methods = new GenericMethods();
        System.out.println("this rectangle is in index "+(methods.linearSearch(rArray,new Rectangle(2,1))+1) +" (0 means not found)");
        //the +1 is bc arrays start at 0


    }
}

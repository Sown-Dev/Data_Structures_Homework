package Practice;

import java.util.ArrayList;

public class SortingAlgs{
    public int[] mergeSort(int[] array){
        if(array.length==1){
            return array;
        }
        int mid=array.length/2;
        int[] arr1=new int[mid];
        int[] arr2=new int[array.length-mid];
        for(int i=0; i<array.length/2; i++){
            arr1[i]=array[i];
            arr2[i]=array[i+(array.length/2)];
        }
        arr1= mergeSort(arr1);
        arr2= mergeSort(arr2);

        return merge(arr1,arr2);

    }

    public int[] merge(int[] a1, int[] a2){
        int[] a3= new int[a1.length+a2.length];
        int i=0, j=0,k=0;
        while(i<a1.length && j<a2.length){
            if(a1[i]<= a2[j]){
                a3[k++] = a1[i++];
            }else{
                a3[k++] = a2[j++];
            }
        }
        while(i<a1.length ){
            a3[k++] =a1[i++];
        }
        while(j<a2.length ){
            a3[k++] = a2[j++];
        }
        return a3;
    }


    

    public static void main(String[] args) {
        int[] sortMe = {5,7,1,29,62,71,7,35,6,45,11,9,15,22,18,20,33,38,60,56,84,49,31};
        SortingAlgs algs= new SortingAlgs();

        int[] m=algs.mergeSort(sortMe);
        for(int i =0;i<sortMe.length;i++) {
            System.out.print(m[i]+ ", ");
        }
    }
}

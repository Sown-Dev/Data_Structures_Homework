package HW8;

import HW8.KBest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<? super T>> implements KBest<T> {
    PriorityQueue<T> q= new PriorityQueue<T>(Collections.reverseOrder());
    int returnAmount=0;

    public KBestCounter(int k){
        returnAmount=k;
    }

    @Override
    public void count(T x) {
        q.offer(x);
    }

    @Override
    public List kbest() {
        ArrayList<T> list = new ArrayList<>();
        PriorityQueue<T> tempQ =new PriorityQueue<T>(q);
        int iter=returnAmount;
        while(!tempQ.isEmpty() && iter>0){
            list.add(tempQ.poll());
            iter--;
        }
        return list;
    }

    public static void main(String[] args) {
        KBestCounter<Integer> test= new KBestCounter<>(20);
        test.count(4);
        test.count(9);
        test.count(42);
        test.count(55);
        test.count(24);
        test.count(11);
        test.count(-4);
        test.count(18);
        System.out.println(test.kbest());
        System.out.println(test.kbest());
        System.out.println(test.kbest());
    }
}
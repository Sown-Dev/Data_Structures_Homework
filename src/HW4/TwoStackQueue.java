package HW4;

import java.util.Stack;

public class TwoStackQueue<T> implements TwoStackQueueInterface<T>{

    private MyStack<T> stack1 = new MyStack<T>();
    private MyStack<T> stack2 = new MyStack<T>();


    @Override
    public void enqueue(T x) {
        while (!stack1.isEmpty())
        {
            stack2.push(stack1.pop());
        }
        stack1.push(x);

        while (!stack2.isEmpty())
        {
            stack1.push(stack2.pop());
        }
    }

    @Override
    public T dequeue() {
        if (stack1.isEmpty())
        {
            return null;
        }
        return stack1.pop();
    }

    @Override
    public int size() {
        return stack1.size();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    public String toString(){
        return stack1.toString()+"\n\n"+stack2.toString();
    }

    public static void main(String[] args){
        TwoStackQueue q = new TwoStackQueue<Double>();

        for(int i=0; i<10;i++){
            q.enqueue(i*2.5);
        }

        System.out.println("Size: "+q.size());

        for(int i=0; i<10;i++){
            System.out.println(q.dequeue());
        }



    }
}
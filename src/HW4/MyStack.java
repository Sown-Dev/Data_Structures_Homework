package HW4;

import java.lang.reflect.Array;

public class MyStack<T> implements MyStackInterface<T> {

    private T[] stackArray;


    private int top;
    private int cap;

    private static final int STARTING_CAP = 10;


    public MyStack() {
        stackArray = (T []) new Object[ STARTING_CAP];
        cap = STARTING_CAP;
        top = -1;
    }

    public void push(T x) {
        if (top == (cap - 1)) {
            T[] old = stackArray;

            stackArray =(T []) new Object[cap * 2 + 1];
            ;
            for (int i = 0; i < cap; i++) {
                stackArray[i] = old[i];
            }
            cap = cap * 2 + 1;

            stackArray[++top] = x;

        } else {

            stackArray[++top] = x;
        }
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            System.exit(-1);
        }


        return stackArray[top--];
    }

    public T peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            System.exit(-1);
        }
        return null;
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }


    public String toString() {

        String ret = "";
        for (int i = size() - 1; i >= 0; i--) {
            ret += "    " + stackArray[i].toString() + "\n";
        }
        ret += "-----------------------------";

        return ret;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack<Integer>();
        for (int i = 0; i < 12; i++) {
            stack.push((Integer) i * 2);
        }

        System.out.println(stack + "\n\n");

        //testing:
        System.out.println(stack.pop() + "\n\n");
        System.out.println(stack.peek() + "\n\n");
        stack.push(4723);

        System.out.println(stack + "\n\n");
    }
}
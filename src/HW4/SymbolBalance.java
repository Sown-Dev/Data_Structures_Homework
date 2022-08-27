package HW4;

import java.util.*;
import java.io.File;
import java.io.*;

public class SymbolBalance implements SymbolBalanceInterface {

    private File file;

    @Override
    public void setFile(String f) {
        File tempfile = new File(f);

        if (tempfile.isFile() &&  tempfile.exists()) {
            file = tempfile;
        } else {
            System.out.println("Error: File doesn't exist or was entered incorrectly");
        }

    }
    /* /*  */
    @Override
    public BalanceError checkFile() throws FileNotFoundException{
        Scanner input = new Scanner(file);
        MyStack<Character> charStack = new MyStack<>();
        int line = 0;
        while (input.hasNextLine()) {
            line++;
            char[] chars = input.nextLine().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                //we check this beforehand in case it changes during the following operations
                boolean empty= charStack.isEmpty();

                //1) Check if is empty and character is an end symbol
                if(empty){
                    if (chars[i] == ')' || chars[i] == '}' || chars[i] == ']') {
                        return new EmptyStackError(line);
                    }
                    if (chars.length>i+1) {
                        if (chars[i] == '*' && chars[i + 1] == '/') {
                            return new EmptyStackError(line);
                        }
                    }
                    //2) if its empty and starts with an opening symbol
                    if (chars[i] == '(' || chars[i] == '{' || chars[i] == '[' || chars[i] == '"') {
                        charStack.push(chars[i]);
                    }
                    if (i > 0) {
                        if (chars[i] == '*' && chars[i - 1] == '/') {
                            charStack.push(chars[i]);
                        }
                    }
                }

                // 3) checks for if stack isnt empty
                if(!empty){
                    // first check if its in a quote
                    if ((charStack.peek() == '"' || charStack.peek() == '*')) {

                        //this chunk checks if the quote is ending, otherwise nothing happens
                        if (chars[i] == '"' && charStack.peek() == '"') {
                            charStack.pop();
                        }
                        if(chars.length>i+1) {
                            if (chars[i] == '*' && chars[i + 1] == '/') {
                                if (charStack.peek() == '*') {
                                    charStack.pop();
                                }
                            }
                        }

                    }else{
                        //everything in here is 100% not in a quote, so can be taken as is.
                        if (chars[i] == '(' || chars[i] == '{' || chars[i] == '['  || chars[i] == '"') {
                            charStack.push(chars[i]);
                        }

                        //unlike quotes, if you put a start comment inside a comment, it does nothing, so you have to check if its in a quote or comment
                        if (i > 0) {
                            if (chars[i] == '*' && chars[i - 1] == '/') {
                                charStack.push(chars[i]);
                            }
                        }

                        if (chars[i] == ')') {
                            if (charStack.peek() == '(') {
                                charStack.pop();
                            } else {
                                return new MismatchError(line, chars[i], charStack.peek());
                            }
                        }
                        if (chars[i] == '}') {
                            if (charStack.peek() == '{') {
                                charStack.pop();
                            } else {
                                return new MismatchError(line, chars[i], charStack.peek());
                            }
                        }

                        if (chars[i] == ']') {
                            if (charStack.peek() == '[') {
                                charStack.pop();
                            } else {
                                return new MismatchError(line, chars[i], charStack.peek());
                            }
                        }


                    }
                }




            }
        }

        //after going through the loop check if stack is empty. if not, return an error, otherwise the everything is good and return null
        if(!charStack.isEmpty()){
            return new NonEmptyStackError(charStack.peek(), charStack.size());
        }else {
            return null;
        }
    }

    public static void main(String[] args){
        SymbolBalance main = new SymbolBalance();
        main.setFile("src/HW4/TestFiles/Test1.java");
        //System.out.println((new File("src/HW4/TestFiles/Test1.java")).getAbsolutePath());
        try {
            main.setFile("src/HW4/TestFiles/Test1.java");
            System.out.println(main.checkFile());

            main.setFile("src/HW4/TestFiles/Test2.java");
            System.out.println(main.checkFile());

            main.setFile("src/HW4/TestFiles/Test3.java");
            System.out.println(main.checkFile());

            main.setFile("src/HW4/TestFiles/Test4.java");
            System.out.println(main.checkFile());

            main.setFile("src/HW4/TestFiles/Test5.java");
            System.out.println(main.checkFile());

            main.setFile("src/HW4/TestFiles/Test6.java");
            System.out.println(main.checkFile());
        }catch (Exception e){
            e.printStackTrace();
        }
        // everything works, but the lines are 2 larger because of the package statement
    }


}

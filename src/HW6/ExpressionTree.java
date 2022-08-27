package HW6;

import java.util.Stack;

public class ExpressionTree implements ExpressionTreeInterface{
    public class ExpressionNode{
        ExpressionNode left;
        ExpressionNode right;
        String val;
        public ExpressionNode(String v){
            val=v;
        }
    }

    ExpressionNode root;

    public ExpressionTree(String expression){
        String[] stringArray = expression.split(" ");
        //first check if expression is valid
        for(int i=0; i<stringArray.length; i++){
            if(stringArray[i].length()<1){
                System.out.println("Error with expression \""+expression+"\": Empty value");
                System.exit(-1);
            }else {
                if ( !isOperator(stringArray[i]) && !isNumber(stringArray[i])) {
                    System.out.println("Error with expression \"" + expression + "\": Invalid character in expression");
                    System.exit(-1);
                }
            }
        }

        Stack<ExpressionNode> cStack = new Stack();
        for(int i=0; i<stringArray.length; i++){
            if(isNumber(stringArray[i]) ){
                ExpressionNode t = new ExpressionNode(stringArray[i]);
                cStack.push(t);
            }
            if( isOperator(stringArray[i]) ){
                if(cStack.size()>=2){
                    ExpressionNode temp = new ExpressionNode(stringArray[i]);
                    temp.right=cStack.pop();  //order matters
                    temp.left=cStack.pop();
                    cStack.push(temp);

                }else{
                    System.out.println("Error with expression \""+expression+"\": Stack is Empty (Too Many Operators)");
                    System.exit(-1);
                }

            }
        }
        //after all this is done, the stack should be left with a single node that becomes the root node.
        if(cStack.size() != 1){
            System.out.println("Error with expression \""+expression+"\": Remaining Operands");
            System.exit(-1);
        }

        root=cStack.pop();

    }

    public boolean isNumber(String str){
        return str.chars().allMatch( Character::isDigit );
    }
    
    public boolean isOperator(String str){
        return (str.length()==1 && isOperator(str.charAt(0)));
    }
    public static boolean isOperator(char ch){
        if(ch=='+' || ch=='-'|| ch=='*' || ch=='/' ){
            return true;
        }
        return false;
    }


    @Override
    public int eval() {
        return ev(root);
    }
    private int ev(ExpressionNode node){
        if(isNumber(node.val)){
            return Integer.parseInt(node.val);
        }
        if(isOperator(node.val)) {
            if (node.val.equals("+")) {
                return ev(node.left) + ev(node.right);
            }
            if (node.val.equals("-")) {
                return ev(node.left) - ev(node.right);
            }
            if (node.val.equals("/")) {
                return ev(node.left) / ev(node.right);
            }
            if (node.val.equals("*")) {
                return ev(node.left) * ev(node.right);
            }
            
        }
        return 0;
    }

    @Override
    public String postfix() {
        return post(root);
    }

    private String post(ExpressionNode node){
        if(node==null){
            return "";
        }else {
            String ret="";
            ret+=post(node.left);
            ret+=post(node.right);
            ret+=node.val;
            ret+=" ";
            return ret;
        }
    }

    @Override
    public String prefix() {
        return pre(root);
    }

    private String pre(ExpressionNode node){
        if(node==null){
            return "";
        }else {
            //this could be done using a variable in the class but I wanted to rely solely on the recursive method
            String ret="";

            ret+=node.val;
            ret+=" ";
            ret+=pre(node.left);
            ret+=pre(node.right);
            return ret;

        }
    }

    @Override
    public String infix() {
       return in(root);
    }

    private String in(ExpressionNode node){
        if(node==null){
            return "";
        }else {
            String ret="";
            ret+=in(node.left);
            ret+=node.val;
            ret+=" ";
            ret+=in(node.right);
            return ret;
        }
    }



    public static void main(String[] args) {
        ExpressionTree tree = new ExpressionTree("15 2 + 1 4 - *");
        System.out.println(tree.prefix());
        System.out.println(tree.postfix());
        System.out.println(tree.infix());
        System.out.println(tree.eval());

        ExpressionTree tree2 = new ExpressionTree("203 + 34  ");
    }

}
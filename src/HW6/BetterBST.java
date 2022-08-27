package HW6;


import java.util.LinkedList;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {

    public BetterBST(){
        super();
    }

    @Override
    int height() {
        return maxH(root);
    }
    private int maxH(BinaryNode<T> node ) {
        if (node == null) {
            return -1;
        }

        int lHeight = maxH(node.left);
        int rHeight = maxH(node.right);

        if (lHeight > rHeight)
            return (lHeight + 1);
        else
            return (rHeight+ 1);
    }


    @Override
    T imbalance() {
        return (T) imbal(root);
    }


    private T imbal(BinaryNode<T> node ) {
        //1 check if node is null
        if(node == null){
            return null;
        }
        //2 check if node is imbalanced
        if(Math.abs(maxH(node.left)-maxH(node.right))>1){
            return node.data;
        }else{
            //3 check if children are imbalanced
            if(imbal(node.left) != null ){
                return imbal(node.left);
            }
            if(imbal(node.right) != null ){
                return imbal(node.right);
            }


        }
        //if none of the statements are true, this node is balanced
        return null;
    }
    private T smallestGreater;
    @Override
    T smallestGreaterThan(T t){
        smallestGreater=null;
       getSmallestGreaterThan(root,t);
       if(smallestGreater==null){
           System.out.println("Error: No value in tree is greater than input");
           //System.exit(-1);
           return null;
       }else{
           return smallestGreater;
       }
    }

    private void getSmallestGreaterThan(BinaryNode<T> node,T t ){
        if(node==null){
            return;
        }else {
            if (node.data.compareTo(t) > 0) {
                smallestGreater = node.data;
                getSmallestGreaterThan(node.left, t);
            }
            if (node.data.compareTo(t) < 0) {
                getSmallestGreaterThan(node.right, t);
            }
            if (node.data.compareTo(t) == 0) {
                getSmallestGreaterThan(node.right, t);
            }
        }
    }


    @Override
    BinarySearchTree<T> mirror() {
        BinarySearchTree<T> newTree=new BetterBST<T>();
        BinaryNode<T>  newRoot=this.root;
        newTree.root=mirrorRec(newRoot);
        return newTree;
    }
    private BinaryNode<T> mirrorRec(BinaryNode<T> node){
        if(node==null){
            return null;
        }
        BinaryNode<T> left=mirrorRec(node.left);
        BinaryNode<T> right=mirrorRec(node.right);

        node.left=right;
        node.right=left;
        return node;
    }

    private LinkedList<BinaryNode<T>> LOTlist= new LinkedList<BinaryNode<T>>();
    @Override
    LinkedList<BinaryNode<T>> levelOrderTraversal() {
        LOTlist= new LinkedList<BinaryNode<T>>();
        int height=height()+1;
        for (int i = 1; i <= height; i++) {
            OrderTraversal(root, i);
        }
        return LOTlist;
    }

    private void OrderTraversal(BinaryNode<T> node, int level)
    {
        if (node == null) {
            return;
        }
        if (level == 1) {
            LOTlist.add(node);
        }
        else {
            if (level > 1) {
                OrderTraversal(node.left, level - 1);
                OrderTraversal(node.right, level - 1);
            }
        }
    }

    @Override
    public String toString() {
        return toStr(root);
    }

    //copied from my previous class for testing purposes
    private String toStr(BinaryNode<T> node){
        if(node==null){
            return "";
        }else {
            String ret="";
            ret+=node.data;
            ret+=" ";
            ret+=toStr(node.left);
            ret+=toStr(node.right);
            return ret;

        }
    }


    public static void main(String[] args) {
        int[] nums ={5,9,12,3,4,-2,17,-3,0,13};
        BetterBST tree= new BetterBST<Integer>();
        for(int i=0; i<nums.length; i++){
            tree.insert(nums[i]);
        }
        //System.out.println(tree.toString());
        System.out.println("Tree height: " +tree.height());
        System.out.println("Tree imbalance: " +tree.imbalance());
        int sgt=-4;
        System.out.println("Smallest greater than "+sgt+": " +tree.smallestGreaterThan(sgt));

        System.out.println("Level order traversal of main tree:");
        LinkedList<BinaryNode<Integer>> LOT=tree.levelOrderTraversal();
        for(int i =0; i<LOT.size();i++){
            System.out.print(LOT.get(i).data +" ");
        }


    }
}
import java.util.function.BiPredicate;

import static java.lang.Math.max;

/**
 * TODO: This is your second major task.
 * <p>
 * This class implements a height-balanced binary search tree,
 * using the AVL algorithm. Beyond the constructor, only the insert()
 * and remove() methods need to be implemented. All other methods are unchanged.
 */

public class AVLTree<K> extends BinarySearchTree<K> {

    /**
     * Creates an empty AVL tree as a BST organized according to the
     * lessThan predicate.
     */
    public AVLTree(BiPredicate<K, K> lessThan) {
        super(lessThan);
    }

    /**
     * TODO
     * Inserts the given key into this AVL tree such that the ordering
     * property for a BST and the balancing property for an AVL tree are
     * maintained.
     */
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return get_height(N.left) - get_height(N.right);
    }

    public Node rightRotate(Node n) {
       Node parent = n.parent;
       Node l = n.left;
       Node right = l.right;
       l.right = n;
       n.parent = l;
       l.parent = parent;

       n.left = right;

       if(right != null){
            right.parent = n;
       }


       if(parent == null){
           root = l;
       }
       else if(parent.right == n){
            parent.right = l;
       }else {
            parent.left = l;
       }
        Node curr = n;
        while (curr != null){
            curr.updateHeight();
            curr = curr.parent;
        }

        return l;
    }

    Node leftRotate(Node n) {
        Node parent = n.parent;
        Node r = n.right;
        Node left = r.left;
        r.left = n;
        n.parent = r;
        r.parent = parent;

        n.right = left;

        if(left != null){
            left.parent = n;
        }
        if(parent == null){
            root = r;
        }
        else if(parent.left == n){
            parent.left = r;
        }else{
            parent.right = r;
        }
        Node curr = n;
        while (curr != null){
            curr.updateHeight();
            curr = curr.parent;
        }

        return r;
    }

    public Node insert(K key) {
        super.insert(key);
        Node parent = search(key).parent;


        while (parent!= null){
//            System.out.println("parent: " + parent.data);
//            System.out.println("added: " + key);
//            System.out.println("is parent AVL? " +parent+ " " + parent.isAVL());
            if(!parent.isAVL()){
//                System.out.println("parent is not AVL");

                if (get_height(parent.right) >= get_height(parent.left)) {
                    if(get_height(parent.right.left) <= get_height(parent.right.right)){
//                        System.out.println("it works LL");
                        leftRotate(parent);
                    }else{
//                        System.out.println("it works RL");
                        rightRotate(parent.right);
                        leftRotate(parent);
                    }
                }else{
                    if(get_height(parent.left.left) < get_height(parent.left.right)){
//                        System.out.println("it works LR");
                        leftRotate(parent.left);
                        rightRotate(parent);
                    }else{
//                        System.out.println("doing RR");
                        rightRotate(parent);
//                        System.out.println(this);
                    }

                }
            }
            parent = parent.parent;
        }
        return search(key);
    }

    public void remove(K key) {
        Node parent = search(key).parent;

        if (contains(key)) {

            super.remove(search(key).data);
            while (parent!= null){
//                System.out.println("parent: " + parent.data);
//                System.out.println("remove: " + key);
//                System.out.println("is parent AVL? " +parent+ " " + parent.isAVL());
                if(!parent.isAVL()){
//                    System.out.println("parent is not AVL");

                    if (get_height(parent.right) >= get_height(parent.left)) {
                        if(get_height(parent.right.left) <= get_height(parent.right.right)){
//                            System.out.println("it works LL");
                            leftRotate(parent);
                        }else{
//                            System.out.println("it works RL");
                            rightRotate(parent.right);
                            leftRotate(parent);
                        }
                    }else{
                        if(get_height(parent.left.left) < get_height(parent.left.right)){
//                            System.out.println("it works LR");
                            leftRotate(parent.left);
                            rightRotate(parent);
                        }else{
//                            System.out.println("doing RR");
                            rightRotate(parent);
//                            System.out.println(this);
                        }

                    }
                }
                parent = parent.parent;
            }
//            while (parent!= null){
//                if(!parent.isAVL()){
//
//                    parent.height = max(get_height(parent.left), get_height(parent.right)) + 1;
//
//                    int balance = getBalance(parent);
//
//                    if (balance > 1 && lessThan.test(parent.data, parent.left.data))
//                        rightRotate(parent);
//
//                    if (balance < -1 && lessThan.test(parent.data, parent.right.data))
//                        leftRotate(parent);
//
//                    if (balance > 1 && lessThan.test(parent.data, parent.left.data)) {
//                        parent.left = leftRotate(parent.left);
//                        rightRotate(parent);
//                    }
//
//                    if (balance < -1 && lessThan.test(parent.data, parent.right.data)) {
//                        parent.right = rightRotate(parent.right);
//                        leftRotate(parent);
//                    }
//                }
//                parent = parent.parent;
//            }
        }



    }

}

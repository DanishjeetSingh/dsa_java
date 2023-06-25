import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * TODO: This is your first major task.
 * <p>
 * This class implements a generic unbalanced binary search tree (BST).
 */

public class BinarySearchTree<K> implements OrderedSet<K> {

    /**
     * A Node is a Location (defined in OrderedSet.java), which means
     * that it can be the return value of a search on the tree.
     */

    public class Node implements Location<K> {

        protected K data;
        protected Node left, right;
        protected Node parent;
        protected int height;

        /**
         * Constructs a leaf node with the given key.
         */
        public Node(K key) {
            this(key, null, null);
        }

        /**
         * TODO
         * <p>
         * Constructs a new node with the given values for fields.
         */
        public Node(K data, Node left, Node right) {
	        this.data = data;
            this.left = left;
            this.right = right;
        }

        /*
         * Provide the get() method required by the Location interface.
         */
        @Override
        public K get() {
            return data;
        }

        /**
         * Return true iff this node is a leaf in the tree.
         */
        protected boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * TODO
         * <p>
         * Performs a local update on the height of this node. Assumes that the
         * heights in the child nodes are correct. Returns true iff the height
         * actually changed. This function *must* run in O(1) time.
         */
        protected boolean updateHeight() {
            int oldHeight = height;
            if(left == null && right == null){
                height = 0;
            }else if( right==null){
                height = left.height + 1;
            }else if( left==null){
                height = right.height + 1;
            }else{
                height = Math.max(left.height, right.height) + 1;
            }
            return oldHeight != height;

        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder predecessor
         * of this node.
         */
        public Node next() {
            if (right == null) {
                return nextAncestor();
            } else {
                return right.first();
            }
        }

        /**
         * TODO
         * <p>
         * Returns the location of the node containing the inorder successor
         * of this node.
         */
        public Node previous() {
            if (left == null) {
                return prevAncestor();
            } else {
                return left.last();
            }
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is less than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        public Node prevAncestor() {
            if (parent != null && this == parent.left) {
                return parent.prevAncestor();
            } else {
                return parent;
            }
        }

        /**
         * TODO
         * <p>
         * This method should return the closest ancestor of node q
         * whose key is greater than q's key. It is not necessary to
         * to perform key comparisons to implement this method.
         */
        public Node nextAncestor() {
            if (parent != null && this == parent.right) {
                return parent.nextAncestor();
            } else {
                return parent;
            }
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the smallest key.
         */
        public Node first() {
            if(left == null){
                return this;
            }else{
                return left.first();
            }
        }

        /*
         * TODO
         * This method should return the node in the subtree rooted at 'this'
         * that has the largest key.
         */
        public Node last() {
            if(right == null){
                return this;
            }else{
                return right.last();
            }
        }

        public boolean isAVL() {
            int h1, h2;
            h1 = get_height(left);
            h2 = get_height(right);
            return Math.abs(h2 - h1) < 2;
        }

        public String toString() {
            return toStringPreorder(this);
        }

    }

    protected Node root;
    protected int numNodes;
    protected BiPredicate<K, K> lessThan;

    /**
     * Constructs an empty BST, where the data is to be organized according to
     * the lessThan relation.
     */
    public BinarySearchTree(BiPredicate<K, K> lessThan) {
        this.lessThan = lessThan;
    }

    /**
     * TODO
     * <p>
     * Looks up the key in this tree and, if found, returns the
     * location containing the key.
     */
    public Node search(K key) {
        Node n = find(key, root, null);
        if (n != null && n.data.equals(key))
            return n;
        else
            return null;
    }

    /**
     * TODO
     * <p>
     * Returns the height of this tree. Runs in O(1) time!
     */
    public int height() {
        return get_height(root);
    }

    /**
     * TODO
     * <p>
     * Clears all the keys from this tree. Runs in O(1) time!
     */
    public void clear() {
        root = null;
        numNodes = 0;
    }

    /**
     * Returns the number of keys in this tree.
     */
    public int size() {
        return numNodes;
    }

    /**
     * TODO
     * <p>
     * Inserts the given key into this BST, as a leaf, where the path
     * to the leaf is determined by the predicate provided to the tree
     * at construction time. The parent pointer of the new node and
     * the heights in all node along the path to the root are adjusted
     * accordingly.
     * <p>
     * Note: we assume that all keys are unique. Thus, if the given
     * key is already present in the tree, nothing happens.
     * <p>
     * Returns the location where the insert occurred (i.e., the leaf
     * node containing the key), or null if the key is already present.
     */
    public Node insert(K key) {
        Node n = find(key, root, null);
        if (n == null){
            root = new Node(key);
            numNodes++;
            return root;
        } else if (lessThan.test(key, n.data)) {
            Node x = new Node(key);
            x.parent = n;
            n.left = x;
            numNodes++;
            while (n!=null && n.updateHeight() ){
                n = n.parent;

            }
            return x;
        }  else if (lessThan.test(n.data, key)) {
            Node x = new Node(key);
            x.parent = n;
            n.right = x;
            numNodes++;
            while (n!=null && n.updateHeight() ){
                n = n.parent;

            }
            return x;
        } else
            return null;
    }

    /**
     * Returns a textual representation of this BST.
     */
    public String toString() {
        return toStringPreorder(root);
    }

    /**
     * Returns true iff the given key is in this BST.
     */
    public boolean contains(K key) {
        Node p = search(key);
        return p != null;
    }

    /**
     * TODO
     * <p>
     * Removes the key from this BST. If the key is not in the tree,
     * nothing happens.
     */

    public void remove(K key) {
        if(numNodes ==1&& root.data.equals(key)){
            clear();
            return;
        }
        Node n = remove_helper(root, key);
        if (n != null) {
            root = n;
        }
    }

    private Node remove_helper(Node n, K key) {
        if (n == null) {
            return null;
        } else if (lessThan.test(key, n.data)) { // remove in left subtree
            n.left = remove_helper(n.left, key);
            if(n.left != null){
                n.left.parent = n;
            }
            Node parent = n;
            while (parent!=null && parent.updateHeight() ){
                parent = parent.parent;

            }
            return n;
        } else if (lessThan.test(n.data, key)) { // remove in right subtree
            n.right = remove_helper(n.right, key);
            if(n.right != null){
                n.right.parent = n;
            }
            Node parent = n;
            while (parent!=null && parent.updateHeight() ){
                parent = parent.parent;

            }
            return n;
        } else { // remove this node
            if (n.left == null) {
                numNodes--;
                return n.right;
            } else if (n.right == null) {
                numNodes--;
                return n.left;
            } else { // two children, replace with first of right subtree
                Node min = n.right.first(); // min == n.right
                n.data = min.data;
                n.right = remove_helper(n.right, n.right.first().data); // another helper function
                if(n.right!= null){
                    n.right.parent = n;
                }
                Node parent = n;
                while (parent!=null && parent.updateHeight() ){
                    parent = parent.parent;

                }
                return n;
            }
        }
    }

    /**
     * TODO
     * <p>
     * Returns a sorted list of all the keys in this tree.
     */
    public List<K> keys() {

        List<K> inorder = new ArrayList<>();
        if(root == null){
            return inorder;
        }
        Node curr  = root.first();
        while (curr != null){
            inorder.add(curr.data);
            curr = curr.next();
        }
        return inorder;
    }

    /**
     * Finds the node with the specified key, or if there is none, the parent of
     * where such a node would be.
     * @param key
     * @param curr  The current node.
     * @param parent  The parent of the current node.
     * @return A node whose data == key or else the parent of where the node would be.
     */
    protected Node find(K key, Node curr, Node parent) {
        if (curr == null)
            return parent;
        else if (lessThan.test(key, curr.data))
            return find(key, curr.left, curr);
        else if (lessThan.test(curr.data, key))
            return find(key, curr.right, curr);
        else
            return curr;
    }

    private String toStringInorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringInorder(p.left);
        if (left.length() != 0) left = left + " ";
        String right = toStringInorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + left + data + right + ")";
    }

    private String toStringPreorder(Node p) {
        if (p == null)
            return ".";
        String left = toStringPreorder(p.left);
        if (left.length() != 0) left = " " + left;
        String right = toStringPreorder(p.right);
        if (right.length() != 0) right = " " + right;
        String data = p.data.toString();
        return "(" + data + "[" + p.height + "]" + left + right + ")";
    }

    /**
     * The get_height method returns the height of the Node n, which may be null.
     */
    protected int get_height(Node n) {
        if (n == null) return -1;
        else return n.height;
    }
}

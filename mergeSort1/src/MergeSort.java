public class MergeSort{

    static Node sort(Node N){
        Node copy = Node.copy(N);
        int length = Node.length(copy);
        if (length <= 1) {
            return copy;
        }
        Node middle = Node.nth_node(copy, length/2);
        Node.nth_node(copy, length/2 - 1).next = null;

        Node left = sort(copy);
        Node right = sort(middle);

        return merge(left, right);
    }

    static Node merge(Node a, Node b){
        Node aCopy = Node.copy(a);
        Node bCopy = Node.copy(b);
        Node result = null;
        if (aCopy == null)
            return bCopy;
        if (bCopy == null)
            return aCopy;

        if (aCopy.data <= bCopy.data) {
            result = aCopy;
            result.next = merge_in_place(aCopy.next, bCopy);
        }
        else {
            result = bCopy;
            result.next = merge_in_place(aCopy, bCopy.next);
        }
        return result;
    }
    static Node sort_in_place(Node N){
//        Node copy = Node.copy(N);
        int length = Node.length(N);
        if (length <= 1) {
            return N;
        }
        Node middle = Node.nth_node(N, length/2);
        Node.nth_node(N, length/2 - 1).next = null;

        Node left = sort_in_place(N);
        Node right = sort_in_place(middle);

        return merge_in_place(left, right);
    }


    static Node merge_in_place(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.data <= b.data) {
            result = a;
            result.next = merge_in_place(a.next, b);
        }
        else {
            result = b;
            result.next = merge_in_place(a, b.next);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] key = {6,2,3,9};
        Node keys = Node.array_to_list(key);
        System.out.println(sort_in_place(keys));
    }
}

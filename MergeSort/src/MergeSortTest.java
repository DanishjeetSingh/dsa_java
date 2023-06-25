import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @org.junit.jupiter.api.Test
    void sort() {
        int[] key1 = {6,2,3,9};
        Node keys1 = Node.array_to_list(key1);
        assertArrayEquals(Node.list_to_array(MergeSort.sort(keys1)), new int[]{2,3,6,9});

        int[] key2 = {0};
        Node keys2 = Node.array_to_list(key2);
        assertArrayEquals(Node.list_to_array(MergeSort.sort(keys2)), new int[]{0});

        int[] key3 = {5,4,3,2,1};
        Node keys3 = Node.array_to_list(key3);
        assertArrayEquals(Node.list_to_array(MergeSort.sort(keys3)), new int[]{1,2,3,4,5});

    }

    @org.junit.jupiter.api.Test
    void merge() {
        Node a1 = Node.array_to_list(new int[] {6,2});
        Node b1 = Node.array_to_list(new int[] {3,9});
        assertArrayEquals(Node.list_to_array(MergeSort.merge(a1,b1)), new int[] {3,6,2,9});

        Node a2 = Node.array_to_list(new int[] {0,1});
        Node b2 = Node.array_to_list(new int[] {2,3});
        assertArrayEquals(Node.list_to_array(MergeSort.merge(a2,b2)), new int[] {0,1,2,3});

        Node a3 = Node.array_to_list(new int[] {8,7});
        Node b3 = Node.array_to_list(new int[] {2});
        assertArrayEquals(Node.list_to_array(MergeSort.merge(a3,b3)), new int[] {2,8,7});
    }

    @org.junit.jupiter.api.Test
    void sort_in_place() {
        int[] key1 = {6,2,3,9};
        Node keys1 = Node.array_to_list(key1);
        assertArrayEquals(Node.list_to_array(MergeSort.sort_in_place(keys1)), new int[]{2,3,6,9});

        int[] key2 = {0};
        Node keys2 = Node.array_to_list(key2);
        assertArrayEquals(Node.list_to_array(MergeSort.sort_in_place(keys2)), new int[]{0});

        int[] key3 = {5,4,3,2,1};
        Node keys3 = Node.array_to_list(key3);
        assertArrayEquals(Node.list_to_array(MergeSort.sort_in_place(keys3)), new int[]{1,2,3,4,5});
    }

    @org.junit.jupiter.api.Test
    void merge_in_place() {
        Node a1 = Node.array_to_list(new int[] {6,2});
        Node b1 = Node.array_to_list(new int[] {3,9});
        assertArrayEquals(Node.list_to_array(MergeSort.merge_in_place(a1,b1)), new int[] {3,6,2,9});

        Node a2 = Node.array_to_list(new int[] {0,1});
        Node b2 = Node.array_to_list(new int[] {2,3});
        assertArrayEquals(Node.list_to_array(MergeSort.merge_in_place(a2,b2)), new int[] {0,1,2,3});

        Node a3 = Node.array_to_list(new int[] {8,7});
        Node b3 = Node.array_to_list(new int[] {2});
        assertArrayEquals(Node.list_to_array(MergeSort.merge_in_place(a3,b3)), new int[] {2,8,7});
    }
}
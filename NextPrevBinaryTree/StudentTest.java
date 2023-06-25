
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void test() throws Exception {
        ArrayList<Integer> tree = new ArrayList<Integer>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        BinaryTree root = new BinaryTree(tree);
        BinaryTree.Iter iter = root.begin();

        for (int i = 0; i < tree.size(); iter.advance()) {
            assertEquals(iter.get(), i + 1);
            ++i;

        }
        ReverseIterator iter2 = root.rbegin();

        for (int i = 6; i > 0; iter2.retreat()) {

            assertEquals(iter2.get(), i + 1);
            i--;
        }

        ArrayList<Integer> tree1 = new ArrayList<Integer>();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        tree1.add(4);
        tree1.add(5);
        tree1.add(6);
        tree1.add(7);
        BinaryTree root1 = new BinaryTree(tree1);
        BinaryTree.Iter iter1 = root1.begin();
        assertEquals(iter1.clone().get(),1);
        assertEquals(iter.equals(iter), true);
    }






}

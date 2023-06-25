import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void test() throws Exception{
        int[] list1 = {5,3,6,4,2,1};
        AVLTree<Integer> bst1 = new AVLTree<>((Integer x, Integer y) -> x<y);
        for (int i = 0; i < list1.length; i++) {
            bst1.insert(list1[i]);
            System.out.println(bst1);
//            System.out.println(bst1.search(list1[i]).parent + " " + list1[i]);
//            if(list1[i] == 2){
//                System.out.println("noice" + bst1.search(3));
//            }
//            System.out.println(bst1);

//            assertEquals(i+1 , bst1.size());
        }
        System.out.println(bst1);










    }
}

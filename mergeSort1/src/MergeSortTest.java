import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {
    @Test
    public void test_sort(){
        int[] key = {0,2,3,4,6,5,6,4,3,2,8,4,9,8};
        Node keys = Node.array_to_list(key);
        assertEquals(sort(keys), {1,2);
    }
}

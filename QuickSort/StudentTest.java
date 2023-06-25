import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void test() throws Exception{
        Integer[] array1 = {-1,-6,4,3,4,1,2,3};
        Integer[] array2 = {-6,-1,1,2,3,3,4,4};

        ArraySequence<Integer> iter1 = new ArraySequence<Integer>(array1);
        QuickSort.quicksort(iter1.begin(), iter1.end());
        assertTrue(Arrays.equals(array1,array2));
    }
}
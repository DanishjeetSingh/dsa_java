import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.BiPredicate;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void test() throws Exception{
        BiPredicate<Integer,Integer> lessEq = (Integer x, Integer y) -> x <= y;
        BinomialQueue<Integer> H1 = new BinomialQueue<>(lessEq);
        BinomialQueue<Integer> H2 = new BinomialQueue<>(lessEq);
        int[] list1 = {1,2,3};
        int[] list2 = {4,5,6};

        for (int i = 0; i < list1.length; i++) {
            H1.push(list1[i]);
        }
        for (int i = 0; i < list2.length; i++) {
            H2.push(list2[i]);
        }
        System.out.println(H1.forest);
        System.out.println(H2.forest);
        System.out.println(BinomialQueue.merge(H1.forest,H2.forest));

    }
}

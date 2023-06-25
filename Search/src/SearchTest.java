import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest {
    @Test
    public void test_find_first_true() {
        boolean[] A = {true, false, true, false, true};
        int begin = 1;
        int end = 3;
        assertEquals(Search.find_first_true(A, begin, end), 2);

        int[] B = {32, 11, 4, 5, 99, 5, 32, 75};
        int x = 5;
        assertEquals(Search.find_first_equal(B,5), 3);
    }
}

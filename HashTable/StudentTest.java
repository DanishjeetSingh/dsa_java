import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StudentTest {
    @Test
    void test() throws Exception{
        HashTable<Integer, Character> table1 = new HashTable<>(4);
        table1.put(2,'n');
//        assertThrows(IllegalArgumentException.class, )
        assertTrue(table1.containsKey(2));
        table1.remove(3);
        assertFalse(table1.containsKey(2));

    }
}

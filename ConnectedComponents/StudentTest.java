import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class StudentTest {
    @Test
    void test() throws Exception{
        UndirectedAdjacencyList list1 = new UndirectedAdjacencyList(10);
        list1.addEdge(0,1);
        list1.addEdge(1,2);
        list1.addEdge(3,4);
        list1.addEdge(4,5);
        list1.addEdge(5,3);
        list1.addEdge(4,6);
        list1.addEdge(6,7);
        list1.addEdge(8,9);
//        for (int i = 10; i < 100 ; i++) {
//            list1.addEdge(10,i);
//        }

        Map<Integer,Integer> map1 = new HashMap<>();
        Map<Integer,Integer> map2 = new HashMap<>();
        map2.put(0,0);
        map2.put(1,0);
        map2.put(2,0);
        map2.put(3,3);
        map2.put(4,3);
        map2.put(5,3);
        map2.put(6,3);
        map2.put(7,3);
        map2.put(8,8);
        map2.put(9,8);
//        for (int i = 10; i < 100; i++) {
//            map2.put(i,10);
//        }
//        map2.put(100,100);
//        map2.put(101,101);

        ConnectedComponents.connected_components(list1, map1);
        Assertions.assertEquals(map1,map2);
    }

}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    public void test() {
        SequenceAligner s1 = new SequenceAligner("CCTGGA","GGACC");
        System.out.println(s1.getAlignedX());
        System.out.println(s1.getAlignedY());
        System.out.println(s1.getScore());
    }

}

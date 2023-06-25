import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

//  @Test
//  public void lookupHuff1() {
//    HuffmanTree ht = new HuffmanTree(new FrequencyTable("aaaabbc"));
//    assertEquals("1", ht.lookup('a'));
//    assertEquals("01", ht.lookup('b'));
//    assertEquals("00", ht.lookup('c'));
//  }
//
//  @Test
//  public void decodeHuff1() {
//    HuffmanTree ht = new HuffmanTree(new FrequencyTable("aaaabbc"));
//    assertEquals('a', ht.decodeChar("1"));
//    assertEquals('b', ht.decodeChar("01"));
//    assertEquals('c', ht.decodeChar("00"));
//  }

  @Test
  public void test() {
    HuffmanTree ht = new HuffmanTree(new FrequencyTable("nnnnnnnnniceiceice"));
    assertEquals('n', ht.decodeChar(ht.lookup('n')));
    assertEquals('i', ht.decodeChar(ht.lookup('i')));
    assertEquals('c', ht.decodeChar(ht.lookup('c')));
    assertEquals('e', ht.decodeChar(ht.lookup('e')));


    HuffmanTree h2 = new HuffmanTree(new FrequencyTable("aaaabbccd"));
    assertEquals('a', h2.decodeChar(h2.lookup('a')));
    assertEquals('b', h2.decodeChar(h2.lookup('b')));
    assertEquals('c', h2.decodeChar(h2.lookup('c')));
    assertEquals('d', h2.decodeChar(h2.lookup('d')));

    HuffmanTree empty = new HuffmanTree(new FrequencyTable(""));
    try{
      empty.lookup('z');
      fail();
    } catch (EncodeException e){
      assertTrue(true);
    }


    HuffmanTree tree = new HuffmanTree(new FrequencyTable("abcd"));
    assertEquals('a', tree.decodeChar("110101010101010"));
    assertEquals("11", tree.lookup('a'));



  }

//  @Test
//  public void decodeHuff2() {
//    HuffmanTree ht = new HuffmanTree(new FrequencyTable("abracadabra"));
//    assertEquals('a', ht.decodeChar("1"));
//    assertEquals('b', ht.decodeChar("01"));
//    assertEquals('c', ht.decodeChar("0001"));
//    assertEquals('d', ht.decodeChar("0000"));
//    assertEquals('r', ht.decodeChar("001"));
//  }



  /**********************************************************************************
   * When you've reached this point, run the Driver and start writing your report.
   **********************************************************************************/

}
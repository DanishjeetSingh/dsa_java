import java.util.ArrayList;
import java.util.Objects;

class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;

    HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode)
    {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
public class HashTable<K,V> implements Map<K,V> {

    private int totalBuckets;
    private int filled_size;

    private ArrayList<HashNode<K, V> > bucketArray;


    public HashTable(int table_size){
        bucketArray = new ArrayList<>();
        totalBuckets = table_size;
        filled_size = 0;
        for (int i = 0; i < totalBuckets; i++)
            bucketArray.add(null);
    };

    private int hashCode (K key) {
        return Objects.hashCode(key);
    }

    private int getBucket(K key){
        int hashCode = key.hashCode();
        int index = hashCode % totalBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }
    @Override
    public V get(K key) throws IllegalArgumentException {
        int index = getBucket(key);
        int hashCode = hashCode(key);

        HashNode<K,V> head = bucketArray.get(index);

        while (head != null){
            if(head.key.equals(key) && head.hashCode == hashCode){
                return head.value;
            }
            head = head.next;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void put(K key, V value) {
        int index = getBucket(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(index);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        filled_size++;
        head = bucketArray.get(index);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(index, newNode);

        if ((1.0 * filled_size) / totalBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            totalBuckets = 2 * totalBuckets;
            filled_size = 0;
            for (int i = 0; i < totalBuckets; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    @Override
    public void remove(K key) {
        int index = getBucket(key);
        int hashCode = hashCode(key);

        HashNode<K,V> head = bucketArray.get(index);
        HashNode<K,V> previous = null;

        while (head != null) {

            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

            previous = head;
            head = head.next;
        }

        filled_size--;

        if (previous != null) {
            assert head != null;
            previous.next = head.next;
        }
        else {
            assert head != null;
            bucketArray.set(index, head.next);
        }

    }

    @Override
    public boolean containsKey(K key) {
        int index = getBucket(key);
        int hashCode = hashCode(key);

        HashNode<K,V> head = bucketArray.get(index);

        while (head != null){
            if(head.key.equals(key) && head.hashCode == hashCode){
                return true;
            }
            head = head.next;
        }
        return false;
    }
}

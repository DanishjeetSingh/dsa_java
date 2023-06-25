public class QuickSort {

    /**
     * TODO
     * @param begin The position of the first element in the sequence to be sorted.
     * @param end   The position that is one-past the last element in the sequence to be sorted.
     * @param <E>   The element type for the sequence.
     */

    public static <E extends Comparable<? super E>>
    void quicksort(Iterator<E> begin, Iterator<E> end) {
        if(!begin.equals(end)) {
            Iterator<E> partition = split(begin,end);
            quicksort(begin, partition);
            partition.advance();
            quicksort(partition, end);
        }
    }

    public static <E extends Comparable<? super E>>
    Iterator<E> split(Iterator<E> begin, Iterator<E> end) {

        Iterator<E> pivot = Algorithms.last(begin, end);
        Iterator<E> i = begin.clone();
        Iterator<E> j = begin.clone();

        while(!j.equals(pivot)) {
            if(j.get().compareTo(pivot.get()) <= 0) {
                Algorithms.iter_swap(i, j);
                i.advance();
            }
            j.advance();
        }

        Algorithms.iter_swap(i, pivot);

        return i;
    }
}







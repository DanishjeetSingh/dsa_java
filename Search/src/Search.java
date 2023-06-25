public class Search {
    public static int find_first_true(boolean[] A, int begin, int end) {
        for (int i = begin; i < end; i++) {
                if(A[i]){
                    return i;
                }
        }
        return end;
    }

    public static int find_first_equal(int[] A, int x) {
        for (int i = 0; i < A.length; i++) {
            if(A[i] == x){
                return i;
            }
        }
        return A.length;
    }

    public static int find_first_true_sorted(boolean[] A, int begin, int end) {
        int mid = (begin + end) / 2;
        if(begin == end){
            return begin;
        }
        else if(A[mid]){
            return find_first_true_sorted(A, begin, mid);
        }
        else{
            return find_first_true_sorted(A, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        boolean[] A = {true, false, true, false, true};
        System.out.println(find_first_true(A, 1, 3));
        int[] B = {32, 11, 4, 5, 99, 5, 32, 75};
        System.out.println(find_first_equal(B, 5));
        boolean[] C = {false, false, true, true, true, true, true};
        System.out.println(find_first_true_sorted(C, 0, 6));

    }

}

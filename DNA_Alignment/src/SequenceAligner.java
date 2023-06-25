import java.util.Random;

/**
 * TODO: Implement the fillCache(), getResult(), and traceback() methods, in
 * that order. This is the biggest part of this project.
 */

public class SequenceAligner {
    private static Random gen = new Random();

    private String x, y;
    private int n, m;
    private String alignedX, alignedY;
    private Result[][] cache;
    private Judge judge;

    /**
     * Generates a pair of random DNA strands, where x is of length n and
     * y has some length between n/2 and 3n/2, and aligns them using the
     * default judge.
     */
    public SequenceAligner(int n) {
        this(randomDNA(n), randomDNA(n - gen.nextInt(n / 2) * (gen.nextInt(2) * 2 - 1)));
    }

    /**
     * Aligns the given strands using the default judge.
     */
    public SequenceAligner(String x, String y) {
        this(x, y, new Judge());
    }

    /**
     * Aligns the given strands using the specified judge.
     */
    public SequenceAligner(String x, String y, Judge judge) {
//        this.x = "CCTGGA";
//        this.y = "GGACC";
        this.x = x.toUpperCase();
        this.y = y.toUpperCase();
        this.judge = judge;
//        n = 6;
//        m = 5;
        n = x.length();
        m = y.length();
        cache = new Result[n + 1][m + 1];
        fillCache();
        traceback();
    }

    /**
     * Returns the x strand.
     */
    public String getX() {
        return x;
    }

    /**
     * Returns the y strand.
     */
    public String getY() {
        return y;
    }

    /**
     * Returns the judge associated with this pair.
     */
    public Judge getJudge() {
        return judge;
    }

    /**
     * Returns the aligned version of the x strand.
     */
    public String getAlignedX() {
        return alignedX;
    }

    /**
     * Returns the aligned version of the y strand.
     */
    public String getAlignedY() {
        return alignedY;
    }

    /**
     * TODO: Solve the alignment problem using bottom-up dynamic programming
     * algorithm described in lecture. When you're done, cache[i][j] will hold
     * the result of solving the alignment problem for the first i characters
     * in x and the first j characters in y.
     * <p>
     * Your algorithm must run in O(n * m) time, where n is the length of x
     * and m is the length of y.
     * <p>
     * Ordering convention: So that your code will identify the same alignment
     * as is expected in Testing, we establish the following preferred order
     * of operations: M (diag), I (left), D (up). This only applies when you
     * are picking the operation with the biggest payoff and two or more
     * operations have the same max score.
     */

    private void fillCache() {
        cache[0][0] = new Result(0, Direction.NONE);
        for (int col = 1; col != m + 1 ; ++col) {
            cache[0][col] = new Result(judge.getGapCost() * col, Direction.LEFT);
        }
        for (int row = 1; row != n + 1; ++row) {
            cache[row][0] = new Result(judge.getGapCost() * row, Direction.UP);
        }
        for (int row = 1; row != n + 1; ++row) {
            for (int col = 1; col != m + 1; ++col) {
                char nextX = x.charAt(row - 1), nextY = y.charAt(col - 1);
                int diag = cache[row - 1][col - 1].getScore() + judge.score(nextX,nextY);
                int left = cache[row][col - 1].getScore() + judge.score(Constants.GAP_CHAR, nextY);
                int up = cache[row - 1][col].getScore() + judge.score(nextX, Constants.GAP_CHAR);
                Result best = new Result(diag, Direction.DIAGONAL);
                if (left > best.getScore()){
                    best = new Result(left, Direction.LEFT);
                }
                if(up > best.getScore()){
                    best = new Result(up, Direction.UP);
                }
                cache[row][col] = best;
            }
        }
    }


    /**
     * TODO: Returns the result of solving the alignment problem for the
     * first i characters in x and the first j characters in y. You can
     * find the result in O(1) time by looking in your cache.
     */
    public Result getResult(int i, int j) {
        return cache[i][j];
    }

    /**
     * TODO: Mark the path by tracing back through parent pointers, starting
     * with the Result in the lower right corner of the cache. Call Result.markPath()
     * on each Result along the path. The GUI will highlight all such marked cells
     * when you check 'Show path'. As you're tracing back along the path, build
     * the aligned strings in alignedX and alignedY (using Constants.GAP_CHAR
     * to denote a gap in the strand).
     * <p>
     * Your algorithm must run in O(n + m) time, where n is the length of x
     * and m is the length of y.
     */
    private void traceback() {
        int row = n;
        int col = m;
        Result curr = cache[row][col];
        StringBuilder X = new StringBuilder();
        StringBuilder Y = new StringBuilder();

        curr.markPath();

        while (row != 0 || col != 0){
            if(curr.getParent() == Direction.DIAGONAL){
                row-=1;
                col-=1;
                X.append(x.charAt(row));
                Y.append(y.charAt(col));
            }else if(curr.getParent() == Direction.LEFT){
                col-=1;
                X.append(Constants.GAP_CHAR);
                Y.append(y.charAt(col));
            }else if(curr.getParent() == Direction.UP){
                row-=1;
                X.append(x.charAt(row));
                Y.append(Constants.GAP_CHAR);

            }
            curr = cache[row][col];
            curr.markPath();
        }
        curr.markPath();

        alignedX = X.reverse().toString();
        alignedY = Y.reverse().toString();
    }

    /**
     * Returns true iff these strands are seemingly aligned.
     */
    public boolean isAligned() {
        return alignedX != null && alignedY != null &&
                alignedX.length() == alignedY.length();
    }

    /**
     * Returns the score associated with the current alignment.
     */
    public int getScore() {
        if (isAligned())
            return judge.score(alignedX, alignedY);
        return 0;
    }

    /**
     * Returns a nice textual version of this alignment.
     */
    public String toString() {
        if (!isAligned())
            return "[X=" + x + ",Y=" + y + "]";
        final char GAP_SYM = '.', MATCH_SYM = '|', MISMATCH_SYM = ':';
        StringBuilder ans = new StringBuilder();
        ans.append(alignedX).append('\n');
        int n = alignedX.length();
        for (int i = 0; i < n; i++)
            if (alignedX.charAt(i) == Constants.GAP_CHAR || alignedY.charAt(i) == Constants.GAP_CHAR)
                ans.append(GAP_SYM);
            else if (alignedX.charAt(i) == alignedY.charAt(i))
                ans.append(MATCH_SYM);
            else
                ans.append(MISMATCH_SYM);
        ans.append('\n').append(alignedY).append('\n').append("score = ").append(getScore());
        return ans.toString();
    }

    /**
     * Returns a DNA strand of length n with randomly selected nucleotides.
     */
    private static String randomDNA(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append("ACGT".charAt(gen.nextInt(4)));
        return sb.toString();
    }

}

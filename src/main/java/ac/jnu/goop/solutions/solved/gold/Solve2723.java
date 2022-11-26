package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved Gold IV
 * 눌러서 잠금 해제
 *
 * @since 22.11.27 AM 01:48
 * @link https://www.acmicpc.net/problem/2723
 */
public class Solve2723 implements SolvedTestable {

    static int[] factorials;
    static int[][] combinations;
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        factorials = new int[12];
        combinations = new int[12][12];
        long[] cache = new long[11];

        int N = Integer.parseInt(reader.readLine());
        for(int testcase = 0 ; testcase < N ; testcase++) {
            int B = Integer.parseInt(reader.readLine());
            if(cache[B-1] == 0) cache[B-1] = find(B, 1);
            writer.write(cache[B-1] + "\n");
        }
        writer.flush();
    }

    private long find(int b, int start) {
        long total = 0;
        for(int r = start ; r <= b ; r++) {
            if(r == 0){
                total++;
                continue;
            }
            long baseCombination = getCombination(b, r);
            long subFind = 1;
            if(b-r > 0) {
                subFind = find(b - r, 0);
            }
            total += baseCombination * subFind;
        }
        return total;
    }

    private int getCombination(int n, int r) {
        if(combinations[n][r] == 0) combinations[n][r] = getFactorial(n) / (getFactorial(r) * getFactorial(n-r));
        return combinations[n][r];
    }

    private int getFactorial(int n) {
        if(n <= 1) return 1;
        if(factorials[n] == 0) {
            factorials[n] = n * getFactorial(n-1);
        }
        return factorials[n];
    }
}

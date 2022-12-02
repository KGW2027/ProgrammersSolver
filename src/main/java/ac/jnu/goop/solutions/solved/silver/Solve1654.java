package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

/**
 * Solved Silver II
 * 랜선 자르기
 *
 * @since 22.12.03 AM 03:04
 * @link https://www.acmicpc.net/problem/1654
 */
public class Solve1654 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int K = read(), N = read();
        long min = 0, max = 0, center;
        int[] cables = new int[K];
        while(K-- > 0){
            cables[K] = read();
            if(cables[K] > max) max = cables[K];
        }

        while(min <= max) {
            center = (min+max)/2;
            if(center == 0) center = 1;
            int v = valid(cables, center);
            if(v < N) max = center-1;
            else min = center+1;
        }
        System.out.print(max);

    }

    static int valid(int[] cables, long len) {
        int cnt = 0;
        for(int cable : cables) cnt += cable/len;
        return cnt;
    }

    static int read() throws Exception {
        int n = 0, b;
        while((b = System.in.read()) > 32) n = n*10+(b-'0');
        return n;
    }
}

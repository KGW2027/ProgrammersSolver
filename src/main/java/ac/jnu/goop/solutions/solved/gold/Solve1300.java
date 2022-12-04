package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

/**
 * Solved Gold II
 * K번째 수
 *
 * @since 22.12.04 PM 16:46
 * @link https://www.acmicpc.net/problem/1300
 */
public class Solve1300 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int N = readInt(), k = readInt();

        long left = 1, right = k;
        while(left <= right) {
            long center = (left+right) / 2;
            long suggestK = getK(N, center);
            if(suggestK < k) left = center + 1;
            else right = center - 1;
        }
        System.out.print(left);
    }

    static long getK(int N, long c) {
        long k = 0;
        for(int i = 1 ; i <= N ; i++) {
            k += Math.min(c/i, N);
        }
        return k;
    }

    static int readInt() throws Exception {
        int n = 0, b;
        while((b= System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}

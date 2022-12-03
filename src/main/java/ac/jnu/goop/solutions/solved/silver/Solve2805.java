package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

/**
 * Solved Silver II
 * 나무 자르기
 *
 * @since 22.12.03 PM 19:58
 * @link https://www.acmicpc.net/problem/2805
 */
public class Solve2805 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int N = read(), M = read(), center, min = 0, max = 0;
        int[] trees = new int[N];
        while(N-- > 0){
            trees[N] = read();
            if(trees[N] > max) max = trees[N];
        }

        while(min <= max) {
            center = (min+max)/2;
            long cut = cut(trees, center);
            if(cut < M) max = center-1;
            else min = center+1;
        }

        System.out.print(max);
    }

    static long cut(int[] arr, int min) {
        long len = 0;
        for(int tree : arr) if(tree > min) len += tree-min;
        return len;
    }

    static int read() throws Exception {
        int n = 0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}

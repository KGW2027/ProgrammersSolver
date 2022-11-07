package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.Arrays;

public class Prob12978 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3, 4},
                {6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4, 4},
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int[][]) args[1], (int) args[2]);
    }

    public int solution(int N, int[][] road, int K) {

        int[][] map = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = 500001;
            }
        }

        for(int[] edge : road) {
            int s = edge[0]-1, y = edge[1]-1;
            int value = map[s][y] > 10000 ? edge[2] : Math.min(edge[2], map[s][y]);
            map[s][y] = value;
            map[y][s] = value;
        }

        int[] dists = map[0];
        boolean[] found = new boolean[N];
        found[0] = true;
        while(!isAllFound(found)) {

            int smallest = Integer.MAX_VALUE;
            int smallestKey = -1;
            for(int x = 1 ; x < dists.length ; x++) {
                if(!found[x] && smallest > dists[x]) {
                    smallest = dists[x];
                    smallestKey = x;
                }
            }
            found[smallestKey] = true;

            for(int update = 1 ; update < dists.length ; update++) {
                if(map[smallestKey][update] > 10000) continue;
                dists[update] = Math.min(dists[update], smallest + map[smallestKey][update]);
            }
        }

        int answer = 1;
        int x = 0;
        while(x < dists.length) if(dists[x++] <= K) answer++;

        return answer;
    }

    private boolean isAllFound(boolean[] b) {
        for(boolean bool : b) if(!bool) return false;
        return true;
    }
}

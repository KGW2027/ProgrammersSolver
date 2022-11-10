package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.SelfTestable;

/**
 * 프로그래머즈 Lv3
 * 네트워크
 *
 * @since 2022.11.10 PM 13:18
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/43162
 */
public class Prob43162 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {3, new int[][]{ {1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2},
                {3, new int[][]{ {1, 1, 0}, {1, 1, 1}, {0, 1, 1}}, 1}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int[][]) args[1]);
    }

    boolean[] connect;
    int[][] network;
    public int solution(int n, int[][] computers) {
        connect = new boolean[n];
        network = computers;

        int answer = 0;
        for(int key = 0 ; key < n ; key++) {
            if(connect[key]) continue;
            find(key);
            answer++;
        }

        return answer;
    }

    private void find(int node) {
        connect[node] = true;
        int[] net = network[node];
        for(int i = 0 ; i < connect.length ; i++) {
            if(net[i] == 1 && !connect[i]) find(i);
        }
    }
}

package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.SelfTestable;


/**
 * 프로그래머즈 Lv3
 * 최고의 집합
 *
 * @since 2022.11.10 PM 13:11
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12938
 */
public class Prob12938 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {2, 9, new int[]{4, 5}},
                {2, 1, new int[]{-1}},
                {2, 8, new int[]{4, 4}}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1]);
    }

    public int[] solution(int n, int s) {
        //각 원수의 합이 S가 되면서 가장 큰 곱을 만들려면 각 수가 모두 S/N에 있어야한다.
        if(n > s) return new int[]{-1};
        int nearFloorValue = Math.floorDiv(s, n);
        int mod = s % nearFloorValue;

        int[] answer = new int[n];
        for(int i = 0 ; i < n ; i++) {
            if(i >= n-mod) answer[i] = nearFloorValue+1;
            else answer[i] = nearFloorValue;
        }

        return answer;
    }
}

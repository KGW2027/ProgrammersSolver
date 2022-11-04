package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

/**
 * Programmers Level 2
 * 삼각 달팽이
 *
 * @since 2022/11/03 21:00
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/68645
 */
public class Prob68645 implements Testable {

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0]);
    }

    public int[] solution(int n) {
        int[][] pyramid = new int[n][n];

        int max = 0;
        for(int i = n ; i > 0 ; i--)
            max += i;

        int count = 0;
        int offset = 0;
        int wall = n;
        // 위에서 왼쪽 아래로 내려감.
        for(int height = 0 ; count < max; height++) {

            pyramid[height][offset] = ++count;

            // 내려가다가 벽에 부딫히면 오른쪽으로 채움
            if(height+1 == wall) {
                for(int right = offset+1 ; right < pyramid[height].length  && count < max; right++) {
                    if(pyramid[height][right] > 0) break;
                    pyramid[height][right] = ++count;
                }

                // 오른쪽 벽에 부딫히면 위로 채움
                int rOffset = offset+1;
                int up = height-1;
                for( ; up > offset ; up--) {
                    int xKey = up-offset;
                    if(pyramid[up][xKey] > 0) break;
                    pyramid[up][xKey] = ++count;
                }
                offset += 1;
                height = up+1;
                wall -= 1;
            }
        }

        // 반환 타입에 맞게 변환
        int[] answer = new int[count];
        int cnt = 0;
        for(int i = 0 ; i < pyramid.length ; i++) {
            for(int j = 0 ; j < pyramid[0].length ; j++) {
                if(pyramid[i][j] > 0) answer[cnt++] = pyramid[i][j];
            }
        }

        return answer;
    }
}

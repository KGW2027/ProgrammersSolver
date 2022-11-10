package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.SelfTestable;

/**
 * 프로그래머즈 Lv3
 * 정수 삼각형
 *
 * @since 2022.11.10 PM 12:35
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */
public class Prob43105 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}, 30}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int[][]) args[0]);
    }

    public int solution(int[][] triangle) {
        // 아래부터 위 숫자랑 더할 때 그 위 숫자와 합쳐질수있는 왼쪽 오른쪽 중 큰 쪽을 반영

        int[][] matrix = new int[triangle.length][triangle.length];
        matrix[0] = triangle[triangle.length-1];
        for(int line = 1 ; line < triangle.length ; line++) {
            int triKey = triangle.length - (line+1);
            for(int key = 0 ; key < triangle[triKey].length ; key++) {
                int baseValue = triangle[triKey][key];
                matrix[line][key] = Math.max(baseValue + matrix[line-1][key], baseValue + matrix[line-1][key+1]);
            }
        }

        int answer = matrix[matrix.length-1][0];

        return answer;
    }
}

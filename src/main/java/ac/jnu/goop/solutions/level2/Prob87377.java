package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Programmers Lv2
 * 교점에 별 만들기
 * 
 * @since 2022/11/05
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/87377
 */
public class Prob87377 implements SelfTestable {

    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new int[][]{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}},
                 new String[]{"....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"}},

                {new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 0, 1}},
                new String[]{"*.*"}},

                {new int[][]{{1, -1, 0}, {2, -1, 0}},
                new String[]{"*"}},

                {new int[][]{{1, -1, 0}, {2, -1, 0}, {4, -1, 0}},
                new String[]{"*"}},
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int[][]) args[0]);
    }

    public String[] solution(int[][] line) {
        // 직선 Ax + By + E = 0과 Cx + Dy + F = 0에서
        // 교점 위치 : ( (BF - ED) / (AD - BC) , ( EC - AF ) / (AD - BC ) )

        // 1. 교점탐색
        List<long[]> crosses = new ArrayList<>();
        long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;

        for (int line1 = 0; line1 < line.length; line1++) {
            int[] l1 = line[line1];
            for (int line2 = line1 + 1; line2 < line.length; line2++) {
                int[] l2 = line[line2];

                long low = (long) l1[0] * l2[1] - (long) l1[1] * l2[0];
                if (low == 0) continue; // 평행 혹은 일치

                long x = ((long) l1[1] * l2[2] - (long) l1[2] * l2[1]);
                long y = ((long) l1[2] * l2[0] - (long) l1[0] * l2[2]);
                if (x % low == 0 && y % low == 0) {
                    x /= low;
                    y /= low;
                    if (minX > x) minX = x;
                    if (x > maxX) maxX = x;
                    if (minY > y) minY = y;
                    if (y > maxY) maxY = y;
                    crosses.add(new long[]{x, y});
                }
            }
        }

        if(crosses.size() == 0) return new String[0];

        // 2. 격자 그리기
        int sizeX = (int) (maxX - minX + 1);
        int sizeY = (int) (maxY - minY + 1);
        char[][] grid = new char[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++)
            for (int x = 0; x < sizeX; x++)
                grid[y][x] = '.';

        for (long[] cross : crosses) {
            int y = (int)(cross[1]-minY);
            int x = (int)(cross[0]-minX);
            grid[y][x] = '*';
        }

        // 3. 반환을 위해 문자열로 변환하기
        String[] answer = new String[sizeY];
        StringBuilder sb;
        int cnt = answer.length;
        for(char[] row : grid) {
            sb = new StringBuilder();
            for(char c : row) sb.append(c);
            answer[--cnt] = sb.toString();
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

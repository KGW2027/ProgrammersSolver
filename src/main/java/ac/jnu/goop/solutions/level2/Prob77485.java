package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

import java.util.Arrays;

/**
 * Programmers Lv2
 * 행렬 테두리 회전하기
 *
 * @since 2022/11/03 PM 11:11
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/77485
 */
public class Prob77485 implements Testable {


    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int)args[1], (int[][]) args[2]);
    }

    public int[] solution(int rows, int columns, int[][] queries) {

        // 초기화
        int[][] grid = new int[rows][columns];
        int cnt = 0;
        for (int y = 0; y < rows; y++)
            for (int x = 0; x < columns; x++)
                grid[y][x] = ++cnt;

        int[][] ref = new int[rows][columns];
        for(int i = 0 ; i < rows ; i++)
            ref[i] = grid[i].clone();

        int[] answer = new int[queries.length];

        for(int qNum = 0 ; qNum < queries.length ; qNum++) {
            int sx = queries[qNum][1]-1, sy = queries[qNum][0]-1;
            int ex = queries[qNum][3]-1, ey = queries[qNum][2]-1;

            int x = sx, y = sy;

            int minNum = ref[y][x];

            for(; x < ex ; x++) {
                if(minNum > ref[y][x]) minNum = ref[y][x];
                grid[y][x+1] = ref[y][x];
            }

            for(; y < ey ; y++) {
                if(minNum > ref[y][x]) minNum = ref[y][x];
                grid[y+1][x] = ref[y][x];
            }

            for(; x > sx ; x--) {
                if(minNum > ref[y][x]) minNum = ref[y][x];
                grid[y][x-1] = ref[y][x];
            }

            for(; y > sy ; y--) {
                if(minNum > ref[y][x]) minNum = ref[y][x];
                grid[y-1][x] = ref[y][x];
            }

            // ref에 grid를 복사
            for(int i = 0 ; i < rows ; i++)
                ref[i] = grid[i].clone();

            answer[qNum] = minNum;
        }

        System.out.println("result : " + Arrays.toString(answer));

        return answer;
    }
}

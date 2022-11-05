package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.Arrays;

/**
 * Programmers Lv 2
 * 3 x n 타일링
 *
 * @since 1차시도 2022.11.03 / 2차시도 2022.11.05 PM 14:20
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12902
 */
public class Prob12902 implements SelfTestable {


    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {4, 11},
                {6, 41},
                {8, 153},
                {10, 571},
                {12, 2131},
                {14, 7953},
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0]);
    }

    public int solution(int n) {

        long[] length = new long[n+1];
        length[0] = 1;
        length[1] = 1;

        int modNumber = (int) (Math.pow(10, 9) + 7);

        // 길이가 2일 때, 경우의수 3
        // 길이가 3일 때, 가장 오른쪽에 1칸을 비운다고 생각하면
        // 길이 2의 경우의수 3 + (오른쪽1칸을 눕히는 경우의수) 1 = 4 * 2(위아래)
        // 길이가 4일 때, (ㄱ모양 + 길이3)*2 + (=모양 길이2)*1
        // 길이 5일 때,
        // 오른쪽 1칸이 세워져있을때는 왼쪽 4칸의 경우의수
        // 오른쪽 1칸이 눕혀져있을 때는, 길이 3의 경우의수
        // ... 일반화
        for(int size = 2 ; size <= n ; size++) {
            if(size%2 == 0) {
                length[size] = length[size - 2] + length[size-1]*2;
            } else {
                length[size] = (length[size - 2] + length[size-1]);
            }
            length[size] %= modNumber;
        }

        System.out.println(Arrays.toString(length));

        return (int) (length[n]);
    }
}

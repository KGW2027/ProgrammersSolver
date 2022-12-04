package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.util.Arrays;

/**
 * Solved Gold IV
 * 공유기 설치
 *
 * @since 22.12.04 PM 14:42
 * @link https://www.acmicpc.net/problem/2110
 */

public class Solve2110 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int N = read(), C = read();
        int[] houses = new int[N];
        while(N-- > 0) houses[N] = read();
        Arrays.sort(houses);

        int left = 1, right = houses[houses.length-1] - houses[0], center;
        while(left <= right) {
            center = (left + right) / 2;
            int place = place(houses, center);
            if(place < C) right = center-1;
            else left = center+1;
        }
        System.out.print(right);
    }

    static int place(int[] houses, int dist) {
        int placed = 1, nowPos = houses[0], nowDist = 0;
        for(int i = 1 ; i < houses.length ; i++) {
            nowDist += houses[i] - nowPos;
            if(nowDist >= dist) {
                placed++;
                nowDist = 0;
            }
            nowPos = houses[i];
        }
        return placed;
    }

    static int read() throws Exception {
        int n = 0, b;
        while((b = System.in.read()) > 32) n = n*10 + (b-'0');
        return n;
    }
}

package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

/**
 * Programmers Lv2
 * 두 큐 합 같게 만들기
 *
 * @since 2022.11.07 AM 09:59
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
public class Prob118667 implements SelfTestable {
    @Override
    public Object solution(Object... args) {
        return solution((int[]) args[0], (int[]) args[1]);
    }

    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}, 2},
                {new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2}, 7},
                {new int[]{1, 1}, new int[]{1, 5}, -1},
        };
    }

    public int solution(int[] queue1, int[] queue2) {

        int q1K = 0, q2K = 0;
        long q1S = 0L, q2S = 0L;
        for(int q1V : queue1) q1S += q1V;
        for(int q2V : queue2) q2S += q2V;
        int queued = 0;

        try {
            while (q1S != q2S) {
                if (q1S > q2S) {
                    int pop = q1K >= queue1.length ? queue2[q1K - queue1.length] : queue1[q1K];
                    q1K++;
                    queued++;
                    q1S -= pop;
                    q2S += pop;
                } else {
                    int pop = q2K >= queue2.length ? queue1[q2K - queue1.length] : queue2[q2K];
                    q2K++;
                    queued++;
                    q2S -= pop;
                    q1S += pop;
                }
            }
        } catch ( IndexOutOfBoundsException e ) { return -1; }

        return queued;
    }
}

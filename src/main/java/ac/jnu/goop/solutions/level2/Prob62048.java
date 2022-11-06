package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Programmers Lv2
 * 멀쩡한 사각형
 *
 * @since 2022.11.06 PM 22:04
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/62048
 */
public class Prob62048 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][] {
                {8, 12, 80L},
                {12, 8, 80L},
                {1, 10000, 0L},
                {7, 3, 12L},
                {8, 3, 14L},
                {3, 7, 12L},
                {100000000, 999999999, 99999998800000002L},
                {2, 1000, 1000L},
                {5, 3, 8L},
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1]);
    }

    public long solution(int w, int h) {
        if(w==h) return ((long) w * h) - w;

        // 기울기 구하기
        int big = Math.max(w, h), small = Math.min(w, h);
        BigDecimal bigIncline = new BigDecimal(big);
        bigIncline = bigIncline.divide(new BigDecimal(small), 25, BigDecimal.ROUND_UP);

        long blocks = 0L;
        for(int x = 0 ; x < small ; x++) {
            blocks += bigIncline.multiply(new BigDecimal(x)).setScale(0, RoundingMode.FLOOR).longValue();
        }

        return blocks*2;
    }
}

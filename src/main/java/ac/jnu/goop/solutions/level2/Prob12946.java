package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmers Level 2
 * 하노이의 탑
 *
 * @since 2022/11/04 AM 12:57
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12946
 */
public class Prob12946 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((int) args[0]);
    }

    public int[][] solution(int n) {

        List<int[]> movelog = new ArrayList<>();
        moveDish(movelog, n, 1, 3);

        return movelog.toArray(new int[0][0]);
    }

    private void moveDish(List<int[]> log, int n, int from, int to) {
        if(n == 1) {
            log.add(new int[]{from, to});
        } else {
            int thirdOne = 0;
            for(int i = 1 ; i <= 3 ; i++) if(i != from && i != to) thirdOne = i;
            moveDish(log, n - 1, from, thirdOne);
            log.add(new int[]{from, to});
            moveDish(log, n - 1, thirdOne, to);
        }
    }
}

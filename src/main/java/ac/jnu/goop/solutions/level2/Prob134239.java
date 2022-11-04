package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmers Lv2
 * 우박수열 정리
 *
 * @since 2022/11/03 PM 19:28 ~ 19:44
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/134239
 */
public class Prob134239 implements Testable {

    public double[] solution(int k, int[][] ranges) {

        // 콜라즈 추측을 따라서 k를 1로 만드는 과정을 저장한다.
        List<Integer> collatz = new ArrayList<>();
        while(k > 1) {
            collatz.add(k);
            if(k % 2 == 0) k /= 2;
            else k = k*3 +1;
        }
        collatz.add(1);

        Integer[] collatzList = collatz.toArray(new Integer[0]);

        // 범위별로 정적분한 뒤 answer에 담는다.
        double[] answer = new double[ranges.length];
        for(int get = 0 ; get < ranges.length ; get++) {
            double area = 0;
            int goalX = collatz.size() + ranges[get][1] - 1;

            // 유효하지 않은 범위에 대한 정적분
            if(ranges[get][0] > goalX) {
                answer[get] = -1;
                continue;
            }

            for(int x = ranges[get][0] ; x < goalX ; x++)
                area += (collatzList[x]+collatzList[x+1])/2.0D;
            answer[get] = area;
        }

        return answer;
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int[][]) args[1]);
    }
}

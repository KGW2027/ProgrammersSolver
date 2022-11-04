package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;


/**
 * Programmers Level 2
 * 카펫 개수
 *
 * @since 2022/11/04 AM 01:15
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */
public class Prob42842 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1]);
    }

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        double sqrt = Math.sqrt(yellow);
        if(isInt(sqrt)) { // 정사각형이 가능하다.
            int isqrt = (int) sqrt;
            return new int[]{isqrt+2, isqrt+2};
        } else { // 한쪽으로 길쭉한 형태다
            int inline;
            for(int row = 1 ; row <= yellow; row++) {
                if(!isInt(yellow / (double)row)) continue;
                inline = yellow / row;
                int brownCheck = row*2 + inline*2 + 4;
                if(brownCheck == brown) return new int[]{inline+2, row+2};
            }
        }

        return answer;
    }

    private boolean isInt(double d) {
        return d - (int) d == 0;
    }
}

package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

/**
 * Programmers Level 2
 * 예상 대진표
 *
 * @since 2022/11/03 21:10
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12985
 */
public class Prob12985 implements Testable {

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1], (int) args[2]);
    }

    public int solution(int n, int a, int b)
    {
        int answer = 1;
        // 높은 쪽이 2의 배수여야하고, a,b의 차이가 1이여야 같은 판이다.
        // => 높은쪽의 2로 나눈 나머지가 0이 아니고, a-b의 절대값이 1보다 크다
        while(Math.max(a, b) % 2 != 0 || Math.abs(a-b) > 1) {
            answer++;
            a = (int) Math.ceil(a/2.0f);
            b = (int) Math.ceil(b/2.0f);
        }

        return answer;
    }
}

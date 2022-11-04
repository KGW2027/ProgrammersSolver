package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;
import java.util.Stack;

/**
 * Programmers Level 2
 * 택배상자 문제
 *
 * @since 2022. 11. 03 PM 13:47
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/131704
 */
public class Prob131704 implements Testable {

    // 일반 컨베이어벨트는 Queue, 보조 컨베이어벨트는 Stack
    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> subConveyor = new Stack<>();
        for(int i = 1 ; i <= order.length ; i++) {
            int canPlace = order[answer];

            // 메인 컨베이어의 제일 앞에 있는 상자가 올릴 수 있는 상자라면 올린다.
            if(canPlace == i) {
                answer++;
                continue;
            } else if(!subConveyor.empty()) {
                int peek = subConveyor.peek();
                // 서브 컨베이어의 제일 앞에 있는 상자가 올릴 수 있는 상자라면 올린다.
                if (canPlace == peek) {
                    answer++;
                    i--;
                    subConveyor.pop();
                    continue;
                }
            }
            // 올릴 수 있는 상자가 없다면 서브 컨베이어로 옮긴다.
            subConveyor.push(i);
        }

        // 메인 컨베이어의 상자는 모두 끝나고 서브 컨베이어만 남은 경우 탐색
        while(!subConveyor.empty() && order[answer] == subConveyor.pop()) answer++;

        return answer;
    }

    @Override
    public Object solution(Object... args) {
        return solution((int[]) args[0]);
    }
}

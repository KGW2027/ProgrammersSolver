package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.SelfTestable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 프로그래머즈 Lv3
 * 이중 우선순위 큐
 * ... 인데 List로 해도 통과됨 시간복잡도 검사가 없나봄
 *
 * @since 2022.11.10 PM 12:39
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42628
 */
public class Prob42628 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][] {
                {new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"}, new int[]{0, 0}},
                {new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}, new int[]{333, -45}},
                {new String[]{"I 10", "I 20", "D 1", "I 30", "I 40", "D -1", "D -1"}, new int[]{40, 40}}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((String[]) args[0]);
    }



    public int[] solution(String[] operations) {

        List<Integer> queue = new ArrayList<>();

        for(String op : operations) {
            char cmd = op.charAt(0);
            int number = Integer.parseInt(op.substring(2));

            switch (cmd) {
                case 'I':
                    int addCheck = queue.size();
                    for (int key = 0; key < queue.size(); key++) {
                        if (queue.get(key) < number) continue;
                        queue.add(key, number);
                        break;
                    }
                    if (addCheck == queue.size()) {
                        queue.add(number);
                    }
                    break;
                case 'D':
                    if (queue.size() > 0) {
                        if (number == 1) queue.remove(queue.size() - 1);
                        else if (number == -1) queue.remove(0);

                    }
                    break;
            }
        }

        if(queue.size() == 0) return new int[2];
        return new int[]{queue.get(queue.size()-1), queue.get(0)};
    }
}

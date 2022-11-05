package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.*;

/**
 * Programmers Lv2
 * 롤케이크 자르기
 *
 * @since 2022.11.05 PM 15:35
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/132265
 */
public class Prob132265 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new int[]{1, 2, 3, 1, 4}, 0},
                {new int[]{1}, 0}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int[]) args[0]);
    }

    public int solution(int[] topping) {

        // 1. 토핑 종류 와 개수 파악
        HashMap<Integer, Integer> typeMap = new HashMap<>();
        for(int t : topping) typeMap.put(t, typeMap.getOrDefault(t, 0)+1);

        int[] clone = topping.clone();
        Arrays.sort(clone);
        int maxToppingNumber = clone[clone.length-1];

        // 좌우의 토핑을 검사하기 위한 변수
        boolean[] leftToppingValid = new boolean[maxToppingNumber];
        int[] rightToppingQty = new int[maxToppingNumber];
        for(int key : typeMap.keySet()) rightToppingQty[key-1] = typeMap.get(key);

        // 토핑의 가짓수
        int leftToppingTypes = 0;
        int rightToppingTypes = typeMap.size();

        // 왼쪽 부터 한 칸 씩 늘리면서 자를 때, 양쪽 토핑 종류 수를 비교합니다.
        // 일치하는 지점부터 불일치하는 지점까지가 가능한 커팅 경우의 수
        int endPoint = 0;
        int startPoint = -1;
        for( ; endPoint < topping.length ; endPoint++) {
            int type = topping[endPoint] - 1;

            // 왼쪽 토핑 추가
            if(!leftToppingValid[type]) {
                leftToppingTypes += 1;
                leftToppingValid[type] = true;
            }

            // 오른쪽 토핑 감소
            if(--rightToppingQty[type] <= 0) {
                rightToppingTypes -= 1;
            }

            if(leftToppingTypes == rightToppingTypes && startPoint == -1) startPoint = endPoint;
            else if(startPoint >= 0 && leftToppingTypes != rightToppingTypes) break;
        }

        if(startPoint == -1) return 0;
        return endPoint - startPoint;
    }
}

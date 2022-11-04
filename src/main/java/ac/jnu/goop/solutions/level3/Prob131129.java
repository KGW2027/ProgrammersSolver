package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.Testable;

import java.util.*;

/**
 * Programmers Lv 3
 * 카운트다운
 *
 * @since 2022/11/04 AM 11:29
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/131129
 */
public class Prob131129 implements Testable {

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0]);
    }

    public int[] solution(int target) {

        // 초기화
        int[] multiplyMap = new int[60];

        // 1. 점수 배열
        List<Integer> scores = new ArrayList<>();
        for (int multiply = 3; multiply > 0; multiply--) {
            for (int score = 1; score <= 20; score++) {
                multiplyMap[score * multiply - 1] = multiply;
                scores.add(score*multiply);
            }
        }

        multiplyMap[49] = 1;

        // 목표 점수가 40이하이고, 한 번의 점수로 나타낼 수 없을 때, 1배수 점수 2개로 만드는 것이 제일 좋다.
        if(target < 40 && !scores.contains(target)) return new int[]{2, 2}; // 다트는 21~39 구간에서 짝수가 아닌수가 해당된다.

        scores.sort(Comparator.reverseOrder());
        Integer[] scoresArray = scores.toArray(new Integer[0]);

        int bestlog = Integer.MAX_VALUE;
        int bestBullAndSingles = 0;
        for(int b = 0 ; b <= target/50 ; b++) {
            int remains = target - (b*50);
            int log = b;
            int bullAndSingles = b;

            for(int score : scoresArray) {
                if(remains >= score) {
                    log += remains/score;
                    if(multiplyMap[score-1] == 1) bullAndSingles += remains/score;
                    remains %= score;
                }
            }

            if(bestlog > log || (bestlog == log && bullAndSingles > bestBullAndSingles)) {
                bestlog = log;
                bestBullAndSingles = bullAndSingles;
            }
        }

        return new int[]{bestlog, bestBullAndSingles};

//        // 목표 점수가 41 이상일 경우 일반적인 계산을 통해 구한다.
//        List<Integer> bestLog = new ArrayList<>();
//        int bullSingle = 0;
//        for(int bull = 0 ; bull <= target/50 ; bull++) { // 점수가 50보다 클 때, 불을 맞춘 경우와 맞추지 않은 경우를 비교
//
//            int remainScore = target - (bull * 50);
//            List<Integer> scoreLog = new ArrayList<>();
//            for(int logBullAdd = 0 ; logBullAdd < bull ; logBullAdd++) scoreLog.add(50);
//
//            int testBullSingle = bull;
//            while (remainScore > 0) {
//                for (int i = 0; i < scores.size(); ) {
//                    int hitScore = scoresArray[i];
//                    if (remainScore >= hitScore) { // 해당 점수를 맞춰도 오버되지 않는 경우
//                        scoreLog.add(hitScore);
//                        scoreMultiply[scoreMap.get(hitScore)-1] += 1;
//                        remainScore -= hitScore;
//                        if(scoreMap.get(hitScore) == 1) testBullSingle++;
//                    } else i++;
//                }
//            }
//
//            if(bestLog.size() == 0 || bestLog.size() > scoreLog.size()){ // best가 지정되지 않았거나, best가 더 많은 다트를 사용했다면, 이번 회차를 선택
//                bestLog = scoreLog;
//                bullSingle = testBullSingle;
//            } else if (bestLog.size() == scoreLog.size() && testBullSingle >= bullSingle) { // 맞춘 다트수가 같으면, 불 싱글을 많이 맞춘 쪽 선택
//                bestLog = scoreLog;
//                bullSingle = testBullSingle;
//            }
//
//        }
//
//        return new int[]{bestLog.size(), bullSingle};
    }
}

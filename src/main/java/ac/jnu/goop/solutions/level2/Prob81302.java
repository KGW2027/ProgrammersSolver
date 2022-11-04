package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmers Level 2
 * 거리두기 확인하기
 *
 * @since 2022/11/04 AM 00:10
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/81302
 */
public class Prob81302 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((String[][]) args[0]);
    }

    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];

        // 모두 거리지키고 있는 상태로 초기화
        for(int i = 0 ; i < places.length ; i++)
            answer[i] = 1;

        for(int room = 0 ; room < places.length ; room++) {

            List<int[]> personPos = new ArrayList<>();

            // 방 정보 초기화
            char[][] roomInfo = new char[5][5];
            for(int y = 0 ; y < 5 ; y++) {
                for(int x = 0 ; x < 5 ; x++) {
                    roomInfo[y][x] = places[room][y].charAt(x);
                    if(roomInfo[y][x] == 'P') personPos.add(new int[]{x, y});
                }
            }

            int[][] pposArr = personPos.toArray(new int[0][0]);

            // 사람 위치 조회
            for(int personCount = 0 ; personCount < pposArr.length ; personCount++) {

                if(answer[room] == 0) break;
                int[] centerPos = pposArr[personCount];

                for(int compareCount = personCount+1 ; compareCount < pposArr.length ; compareCount++) {
                    int[] targetPos = pposArr[compareCount];

                    int distX = Math.abs(centerPos[0] - targetPos[0]);
                    int distY = Math.abs(centerPos[1] - targetPos[1]);
                    // 거리가 2이하인 경우는 1. 일직선으로 앉은 경우, 2.대각선으로 앉은 경우
                    if(distX + distY > 2) continue;

                    if(distX == 1 && distY == 1) { // 대각선으로 앉은 경우
                        if(roomInfo[targetPos[1]][centerPos[0]] == 'X' && roomInfo[centerPos[1]][targetPos[0]] == 'X') continue; // 대각선 위치에 모두 파티션이 있는 경우 통과
                    } else if (distX == 2 || distY == 2) { // 2칸 거리의 일직선상에 앉아있는 경우
                        if(roomInfo[(centerPos[1]+targetPos[1])/2][(centerPos[0]+targetPos[0])/2] == 'X') continue; // 두 사람 사이에 파티션이 있는 경우 통과
                    }
                    answer[room] = 0;
                    break;
                }
            }
        }

        return answer;
    }
}

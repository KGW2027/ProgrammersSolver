package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.Testable;

import java.util.ArrayList;
import java.util.List;

/**
 * 체육관 라커 문제 (실패)
 *
 */

public class Prob1838 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1], (int[][]) args[2]);
    }

    public int solution(int n, int m, int[][] timetable) {

        if(m<=1) return 0;

        int[] entranceLog = new int[1320 - 600 + 2];
        for(int[] ttb : timetable) {
            entranceLog[ttb[0] - 600] += 1;
            entranceLog[ttb[1]+1 - 600] -= 1;
        }

        int peeks = 0;
        int change = 0;
        for(int time = 0 ; time < entranceLog.length ; time++) {
            change += entranceLog[time];
            if(change > peeks) peeks = change;
        }

        if(peeks <= 1) return 0;

        int maxDistance = 2 * (n-1);
        while(!validDistance(n, peeks, maxDistance)) maxDistance--;

        return maxDistance;
    }

    private boolean validDistance(int row, int man, int testDistance) {

        List<int[]> poses = new ArrayList<>();
        poses.add(new int[]{0, 0});

        boolean isSettable;
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < row; x++) {

                isSettable = true;
                for (int[] claim : poses) {
                    int dist = Math.abs(claim[0] - x) + Math.abs(claim[1] - y);
                    if (dist < testDistance) { // 자리를 차지한 사람들과 거리가 testDistance 보다 짧은 곳이 있다면 배치할 수 없는 곳이다.
                        isSettable = false;
                        break;
                    }
                }

                if(isSettable) poses.add(new int[]{x, y});
            }
        }

        System.out.println(testDistance + " << " );

        return poses.size() >= man;
    }
}

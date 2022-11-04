package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.Testable;

import java.util.Arrays;

public class Prob17678 implements Testable {
    @Override
    public Object solution(Object... args) {
        return null;
    }

    /**
     * Programmers Lv 3
     * 통근버스
     *
     * @since 2022/11/04 AM 10:18
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/17678
     */
    public String solution(int n, int t, int m, String[] timetable) {

        Arrays.sort(timetable);

        int startTime = 9*60;
        int endTime = 23*60+59;

        String answer = "";

        int rodes = 0; // 총 탑승자 수
        for(int bus = 0 ; bus < n && startTime < endTime ; bus++) {

            // 1. 첫차부터 몇 명씩 태워서 출발하는지 계산한다.
            int thisBusRider = 0;
            for(int ridePerson = 0 ; ridePerson < m ; ridePerson++) {
                int checkNum = rodes+ridePerson;
                if(checkNum >= timetable.length) break;
                String[] split = timetable[checkNum].split(":");
                int compareTime = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
                if(startTime >= compareTime)
                    thisBusRider += 1;
            }
            rodes += thisBusRider;

            // 2. 막차인 경우 탑승을 테스트한다.
            if(bus == n-1) {
                int remainSit = m-thisBusRider;
                // 막차에 자리가 남아있다면, 버스가 도착하는 시간에 맞춰서 온다.
                if(remainSit > 0)
                {
                    answer = String.format("%02d:%02d", startTime/60, startTime%60);
                }
                // 자리가 없다면, 가장 늦게도착한 탑승자보다 1분 빨리온다.
                else
                {
                    String[] split = timetable[rodes-1].split(":");
                    int lastRider = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                    answer = String.format("%02d:%02d", (lastRider-1)/60, (lastRider-1)%60);
                }
            }

            startTime+=t;
        }

        return answer;
    }

}

package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Prob42888 implements Testable {

    @Override
    public Object solution(Object... args) {
        return solution((String[]) args[0]);
    }

    /**
     * Programmers Lv2
     * 오픈채팅방
     *
     * @since 2022/11/03 PM 10:15
     * @link https://school.programmers.co.kr/learn/courses/30/lessons/42888
     */
    // 대화방 문제
    public String[] solution(String[] record) {

        String[] msgFormat = new String[] {
                "%uid%님이 들어왔습니다.", // 0 : Enter
                "%uid%님이 나갔습니다." // 1 : Exit
        };

        HashMap<String, String> uid = new HashMap<>();
        List<String> logs = new ArrayList<>();

        // 로그 등록
        for(String log : record) {
            String[] split = log.split(" ");
            String userId = split[1];
            switch(split[0]) {
                case "Enter":
                    String nick = split[2];
                    logs.add(msgFormat[0].replace("%uid%", "%"+userId+"%"));
                    uid.put(userId, nick);
                    break;
                case "Leave":
                    logs.add(msgFormat[1].replace("%uid%", "%"+userId+"%"));
                    break;
                case "Change":
                    uid.put(userId, split[2]);
                    break;
            }
        }

        // 닉네임 적용
        String[] answer = new String[logs.size()];
        for(int line = 0 ; line < logs.size() ; line++) {
            String log = logs.get(line);
            String userId = log.split("%")[1];
            answer[line] = log.replace("%"+userId+"%", uid.get(userId));
        }

        return answer;
    }
}

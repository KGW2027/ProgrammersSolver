package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 프로그래머즈 Lv2
 * [3차] 방금그곡
 *
 * @since 2022.11.08 PM 23:08 1차 포기. 이유를 모르겠음
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/17683#qna
 */
public class Prob17683 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
//                {"ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}, "HELLO"},
//                {"CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}, "FOO"},
//                {"ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}, "WORLD"},
//                {"ABC", new String[]{"12:00,12:06,HELLO,ABC#ABC#ABC"}, "(None)"},
//                {"ABC", new String[]{"12:00,12:10,HELLO,ABC#ABC#ABC"}, "HELLO"},
//                {"ABC", new String[]{"12:04,13:00,HELLO,ABC#ABC#ABC"}, "HELLO"},
//                {"C#C", new String[]{"12:00,12:06,HELLO,C#C#CC#"}, "HELLO"},
//                {"ABC", new String[]{"14:00,14:15,ABCDE,ABCDE", "02:00,02:15,FGHJI,ABCDE"}, "FGHJI"},
                {"CC#BCC#BCC#", new String[]{"03:00,03:08,FOO,CC#B"}, "FOO"}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((String) args[0], (String[]) args[1]);
    }

    public String solution(String m, String[] musicinfos) {

        HashMap<String, Integer> playTimes = new HashMap<>();
        HashMap<String, Integer> startTimes = new HashMap<>();

        char[] find = exchangeSharpNode(m).toCharArray();

        for(String music : musicinfos) {
            int correct = 0;

            String[] infos = music.split(",");
            String[] stinfo = infos[0].split(":");
            String[] etinfo = infos[1].split(":");

            int start = Integer.parseInt(stinfo[0]) * 60 + Integer.parseInt(stinfo[1]);
            int end = Integer.parseInt(etinfo[0]) * 60 + Integer.parseInt(etinfo[1]);
            if(infos[1].equals("00:00")) end = 24*60 + 60;

            char[] songInfo = exchangeSharpNode(infos[3]).toCharArray();
            int key = 0;
            for (int i = 0; start + i < end; i++) {
                if(songInfo[key++] == find[correct]) correct++;
                if(key == songInfo.length) key = 0;
                if(correct == find.length) {
                    playTimes.put(infos[2], end-start);
                    startTimes.put(infos[2], start);
                    break;
                }
            }
        }

        if(playTimes.size() == 0) return "(None)";

        String answer = "";
        int length = 0;
        for(String songName : playTimes.keySet()) {
            int songLength = playTimes.get(songName);
            if(songLength > length || (songLength == length && startTimes.get(answer) > startTimes.get(songName))) {
                answer = songName;
                length = songLength;
            }
        }

        return answer;
    }

    private String exchangeSharpNode(String str) {
        return str.replace("C#", "1")
                .replace("D#", "2")
                .replace("F#", "3")
                .replace("G#", "4")
                .replace("A#", "5");
    }
/*
    public String solution(String m, String[] musicinfos) {
        HashMap<String, Integer> answers = new HashMap<>();
        HashMap<String, Integer> startTime = new HashMap<>();

        List<String> find = new ArrayList();
        char[] mArray = m.toCharArray();
        String text;
        for (int i = 0; i < m.length(); ) {
            text = String.valueOf(mArray[i++]);
            if (i < m.length() && mArray[i] == '#') text = text.concat(String.valueOf(mArray[i++]));
            find.add(text);
        }
        String[] query = find.toArray(new String[0]);

        for (String music : musicinfos) {
            int correct = 0;

            String[] infos = music.split(",");
            String[] stinfo = infos[0].split(":");
            String[] etinfo = infos[1].split(":");

            int start = Integer.parseInt(stinfo[0]) * 60 + Integer.parseInt(stinfo[1]);
            int end = Integer.parseInt(etinfo[0]) * 60 + Integer.parseInt(etinfo[1]);

            char[] songArray = infos[3].toCharArray();
            int key = 0;
            String songdata;
            for (int i = 0; start + i < end; i++) {
                songdata = String.valueOf(songArray[key++]);
                if (key < songArray.length && songArray[key] == '#')
                    songdata = songdata.concat(String.valueOf(songArray[key++]));
                if (key == songArray.length) key = 0;

                if (query[correct].equals(songdata)) correct++;
                if (correct == query.length) {
                    answers.put(infos[2], end - start);
                    startTime.put(infos[2], start);
                    break;
                }
            }
        }

        if (answers.size() == 0) return "(None)";

        String select = "";
        int songLength = 0;
        for (String songName : answers.keySet()) {
            int length = answers.get(songName);
            if (length > songLength) { // 재생시간 비교
                select = songName;
                songLength = length;
            } else if (length == songLength) { // 등록 시간 비교
                if(startTime.get(select) > startTime.get(songName)) {
                    select = songName;
                }
            }
        }

        return select;
    }*/
}

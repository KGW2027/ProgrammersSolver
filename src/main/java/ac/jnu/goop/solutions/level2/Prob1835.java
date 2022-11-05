package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

/**
 * Programmers Lv2
 * 단체사진 찍기
 *
 * @since 2022/11/05
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/1835#qna
 */
public class Prob1835 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {2, new String[]{"N~F=0", "R~T>2"}, 3648},
                {2, new String[]{"M~C<2", "C~M>1"}, 0}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (String[]) args[1]);
    }

    char[] person;
    String[] data;
    public int solution(int n, String[] data) {
        // 경우의 수는 8!
        person = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        this.data = data;

        boolean[] placed = new boolean[person.length];

        return simulate(placed, new char[person.length], 0);
    }

    private int simulate(boolean[] v, char[] caseInfo, int location) {
        int valid = 0;
        if(location == person.length) {
            char[] cond;
            char p1, p2;
            int p1loc, p2loc, dist, wantdist;
            for (String condData : data) {

                cond = condData.toCharArray();

                p1 = cond[0];
                p2 = cond[2];
                p1loc = -1;
                p2loc = -1;

                for (int ploc = 0; ploc < caseInfo.length; ploc++) {
                    if (p1loc >= 0 && p2loc >= 0) break;
                    if (caseInfo[ploc] == p1) p1loc = ploc;
                    else if (caseInfo[ploc] == p2) p2loc = ploc;
                }

                dist = Math.abs(p1loc - p2loc) - 1;
                wantdist = Integer.parseInt(String.valueOf(cond[4]));
                if (cond[3] == '=' && dist != wantdist) return 0; // N~F=0 : N과 F의 dist가 0이 아닐 때 불만족
                else if (cond[3] == '>' && dist <= wantdist) return 0; // C~M>1 : C와 M의 dist가 1보다 작거나 같으면 불만족
                else if (cond[3] == '<' && dist >= wantdist) return 0; // M~C<2 : M과 C의 dist가 2보다 크거나 같으면 불만족
            }

            return 1;
        } else {
            for (int i = 0; i < person.length; i++) {
                if (v[i]) continue;
                char[] caseClone = caseInfo.clone();
                boolean[] validClone = v.clone();
                caseClone[location] = person[i];
                validClone[i] = true;
                valid += simulate(validClone, caseClone, location + 1);
            }
        }

        return valid;
    }
}

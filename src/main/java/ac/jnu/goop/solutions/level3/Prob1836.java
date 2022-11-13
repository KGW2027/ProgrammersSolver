package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.SelfTestable;

/**
 * 프로그래머즈 Lv3
 * 리틀 프렌즈 사천성
 *
 * @since 2022.11.13 PM 16:35
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/1836
 */
public class Prob1836 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
//                {3, 3, new String[]{"DBA", "C*A", "CDB"}, "ABCD"},
//                {2, 4, new String[]{"NRYN", "ARYA"}, "RYAN"},
//                {4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}, "MUZI"},
//                {2, 2, new String[]{"AB", "BA"}, "IMPOSSIBLE"},
//
//                {1, 2, new String[]{"AA"}, "A"},
//                {2, 2, new String[]{"ZA", "ZA"}, "AZ"},
//                {3, 3, new String[]{"A.B", "B.A", "C.C"}, "IMPOSSIBLE"},
//                {3, 3, new String[]{"AZA", "BZB", "*.*"}, "ZAB"},
//                {1, 7, new String[]{"ABC.CBA"}, "CBA"},
//                {6, 1, new String[]{"A", "B", "E", "E", "B", "A"}, "EBA"},
//                {4, 4, new String[]{"A..C", "..CB", "B...", "...A"}, "BAC"},
//                {3, 3, new String[]{"CCB", "A.B", "AEE"}, "ABCE"},
                {10, 10, new String[]{"M...M...DU", "....AR...R", "...E..OH.H", ".....O....", ".E..A..Q..", "Q....LL.*.", ".D.N.....U", "GT.T...F..", "....FKS...", "GN....K..S"}, "AEFGHKLMDOQRSTNU"}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int) args[1], (String[]) args[2]);
    }

    public String solution(int m, int n, String[] board) {

        // 1. 맵 구성
        char[][] map = new char[m][n];
        for(int i = 0 ; i < board.length ; i++)  map[i] = board[i].toCharArray();

        // 2. 같은 문자 좌표 매칭
        int[][][] match = new int[26][2][2];
        boolean[] hasChar = new boolean[26];
        for(int y = 0 ; y < map.length ; y++) {
            for(int x = 0 ; x < map[0].length ; x++) {
                if(map[y][x] == '.' || map[y][x] == '*') continue;

                int key = map[y][x] - 'A';
                hasChar[key] = true;

                int[] posInfo = match[key][0];
                if(posInfo[0] == 0 && posInfo[1] == 0) match[key][0] = new int[]{x+1, y+1};
                else match[key][1] = new int[]{x+1, y+1};
            }
        }

        // 3. 앞문자부터 매칭탐색
        StringBuilder builder = new StringBuilder();
        boolean found;
        for(int c = 0 ; c < 26 ; c++) {
            if(!hasChar[c]) continue;

            char ch = (char) ('A' + c);
            found = valid(map, match[c], ch);
            if(found) {
                hasChar[c] = false;
                c = -1;
                builder.append(ch);
            }
        }

        final String failMessage = "IMPOSSIBLE";
        if(builder.length() == 0) return failMessage;
        for(boolean b : hasChar) if(b) return failMessage;

        return builder.toString();
    }

    private boolean valid(char[][] map, int[][] posInfo, char ch) {
        int[] pos1 = posInfo[0], pos2 = posInfo[1];

        int sx = Math.min(pos1[0], pos2[0])-1, ex = Math.max(pos1[0], pos2[0])-1;
        int sy = Math.min(pos1[1], pos2[1])-1, ey = Math.max(pos1[1], pos2[1])-1;
        boolean syMoveX = true, eyMoveX = true;
        boolean sxMoveY = true, exMoveY = true;

        // X 이동 검증
        if(Math.abs(pos1[0] - pos2[0]) > 0) {
            // 똑같은 문자거나 빈칸이면 통과
            for(int tx = sx; tx <= ex ; tx++) {
                if(syMoveX && map[sy][tx] != ch && map[sy][tx] != '.') syMoveX = false;
                if(eyMoveX && map[ey][tx] != ch && map[ey][tx] != '.') eyMoveX = false;
                if(!syMoveX && !eyMoveX) break;
            }
        }

        // Y 이동 검증
        if(Math.abs(pos1[1] - pos2[1]) > 0) {
            // 똑같은 문자거나 빈칸이면 통과
            for(int ty = sy; ty <= ey ; ty++) {
                if(sxMoveY && map[ty][sx] != ch && map[ty][sx] != '.') sxMoveY = false;
                if(exMoveY && map[ty][ex] != ch && map[ty][ex] != '.') exMoveY = false;
                if(!sxMoveY && !exMoveY) break;
            }
        }

        float d = ((float) (pos1[1] - pos2[1])) / ((float) (pos1[0] - pos2[0]));
        boolean condition = ( d > 0 ? (syMoveX && exMoveY) || (eyMoveX && sxMoveY) : (sxMoveY && syMoveX) || (eyMoveX && exMoveY) );
        if(condition) {
            map[pos1[1]-1][pos1[0]-1] = '.';
            map[pos2[1]-1][pos2[0]-1] = '.';
            return true;
        } else return false;
    }
}

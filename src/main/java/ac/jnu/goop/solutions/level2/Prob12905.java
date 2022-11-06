package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmers Lv2
 * 가장 큰 정사각형 찾기
 *
 * @since 2022.11.07 AM 12:03
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12905
 */
public class Prob12905 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
//                {new int[][]{{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}}, 9},
//                {new int[][]{{0,0,1,1},{1,1,1,1}}, 4},
//                {new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, 1},
//                {new int[][]{{1, 1, 1, 1}, {0, 1, 1, 1}, {0, 1, 1, 1}}, 9},
//                {new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}, 1},
//                {new int[][]{{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}}, 9},
                {new int[][]{{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 0, 0}}, 25},
                {new int[][]{{0, 0, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}}, 25},
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int[][]) args[0]);
    }

    public int solution(int[][] board) {
        int y = 0, bigSize = 0;
        int continued;
        int[] xTest = new int[board[0].length];
        int[] xTesting = new int[board[0].length];
        while (board.length > y) {
            continued = 0;

            for (int x = 0; x < board[0].length; x++) {
                if (board[y][x] == 0) {
                    xTest[x] = 0;
                    xTesting[x] = 0;
                    continued = 0;

                } else {
                    continued++;

                    if (xTest[x] == 0 && continued > bigSize) { // 테스트 중이 아니라면 테스트 시작
                        xTest[x] = continued;
                        xTesting[x] = continued - 1;
                    } else if (xTest[x] > 0) { // x가 테스트중일 때,
                        if (continued >= xTest[x]) { // 현재 연속된 1의 개수도 가능하다면 테스트 1줄 추가
                            xTesting[x]--;
                        } else if (continued < xTest[x] && continued > bigSize) { // 테스트보다 낮지만 bigSize보다 크면 낮춰서 진행
                            int gap = xTest[x] - continued;
                            xTest[x] = continued;
                            xTesting[x] -= (gap + 1);
                        } else { // 현재 위치의 연속횟수가 bigSize보다 낮다면 테스트 초기화
                            xTest[x] = 0;
                            xTesting[x] = 0;
                        }
                    }

                    if (xTesting[x] <= 0 && xTest[x] > bigSize) {
                        bigSize = xTest[x]; // xTesting이 0이되면 최대사이즈 갱신
                        xTest[x] = 0;
                        xTesting[x] = 0;
                    }

                }
            }
            y++;
        }

        return bigSize * bigSize;
    }
/*
        int[][] cntArr = new int[board.length][board[0].length];
        for(int y = 0 ; y < board.length ; y++) {
            int continued = 0;
            for(int x = 0 ; x < board[0].length ; x++) {
                if(board[y][x] == 0) continued = 0;
                else cntArr[y][x] = ++continued;
            }
        }

        int bigsize = 0;
        for(int y = 0 ; y < board.length ; y++) {
            for(int x = 0 ; x < board[0].length ; x++) {
                int count = cntArr[y][x];
                if(count > bigsize) {
                    boolean pass;
                    do {
                        pass = true;
                        if(y+count > cntArr.length){ pass = false; continue; }
                        for (int check = 1; check < count; check++) {
                            if (cntArr[y + check][x] < count) {
                                pass = false;
                                break;
                            }
                        }
                        if (pass) bigsize = count;
                    }while(!pass && --count > bigsize);
                }
            }
        }

        return bigsize * bigsize;
        */

        /*
                int[][] rowArr = new int[board.length][board[0].length];
        int[][] colArr = new int[board.length][board[0].length];

        for(int y = 0 ; y < board.length ; y++) {
            int continued = 0;
            for(int x = 0 ; x < board[0].length ; x++) {
                if(board[y][x] == 0) continued = 0;
                else rowArr[y][x] = ++continued;
            }
        }

        for(int x = 0 ; x < board[0].length ; x++) {
            int continued = 0;
            for(int y = board.length - 1 ; y >= 0 ; y--) {
                if(board[y][x] == 0) continued = 0;
                else colArr[y][x] = ++continued;
            }
        }

        for(int[] b : board) System.out.println(Arrays.toString(b));
        System.out.println();
        for(int[] c : colArr) System.out.println(Arrays.toString(c));

        int maxSize = 0;
        for(int y = 0 ; y < board.length ; y++) {
            for(int x = 0 ; x < board[0].length ; x++) {
                if(rowArr[y][x] > maxSize && colArr[y][x] > maxSize) {
                    int testSize = Math.min(rowArr[y][x], colArr[y][x]);
                    boolean pass = true;
                    for(int sub = 1 ; sub < testSize ; sub++) {
                        if(colArr[y][x-sub] < testSize) { pass=false; break; }
                    }
                    if(pass) maxSize = testSize;
                }
            }
        }

        return maxSize * maxSize;

         */
}


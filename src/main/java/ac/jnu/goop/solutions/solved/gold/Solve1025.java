package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved Gold V
 * 제곱 수 찾기
 *
 * @since 2022.11.11 AM 11:
 * @link https://www.acmicpc.net/problem/1025
 */
public class Solve1025 implements SolvedTestable {
    @Override
    public void solution() {
        try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            String[] inputSize = inputReader.readLine().split(" ");
            char[][] matrix = new char[Integer.parseInt(inputSize[0])][];

            for (int dataInput = 0; dataInput < matrix.length; dataInput++) {
                matrix[dataInput] = inputReader.readLine().toCharArray();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            bw.write(String.valueOf(find(matrix)));
            bw.flush();
            bw.close();
            inputReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int find(char[][] matrix) {
        int max = -1;
        // 1,1 행렬의 경우 find(char[], int, int)에서 xGap, yGap 이 모두 0인경우를 스킵하기 떄문에 탐색 불가능
        // Manually하게 검증
        if(matrix.length == 1 && matrix[0].length == 1) {
            int number = Integer.parseInt(String.valueOf(matrix[0][0]));
            return check(number) ? number : -1;
        }

        for(int y = 0 ; y < matrix.length ; y++) {
            for(int x = 0 ; x < matrix[0].length ; x++) {
                max = Math.max(find(matrix, x, y), max);
            }
        }
        return max;
    }

    private int find(char[][] matrix, int x, int y) {
        int max = -1;
        StringBuilder ixiytester = new StringBuilder();
        StringBuilder ixdytester = new StringBuilder();
        StringBuilder dxdytester = new StringBuilder();
        StringBuilder dxiytester = new StringBuilder();
        boolean ixiyReachEnd, ixdyReachEnd, dxdyReachEnd, dxiyReachEnd;
        for (int xGap = 0; xGap < matrix[0].length; xGap++) {
            for (int yGap = 0 ; yGap < matrix.length ; yGap++) {
                if(xGap == 0 && yGap == 0) continue;

                ixiytester.setLength(0);
                ixiyReachEnd = false;
                ixdytester.setLength(0);
                ixdyReachEnd = false;
                dxdytester.setLength(0);
                dxdyReachEnd = false;
                dxiytester.setLength(0);
                dxiyReachEnd = false;

                for(int multiplier = 0 ; ; multiplier++) {
                    if(ixiyReachEnd && ixdyReachEnd && dxiyReachEnd && dxdyReachEnd) break;

                    int xc = multiplier * xGap, yc = multiplier * yGap;

                    int ix = x + xc, iy = y + yc;
                    int dx = x - xc, dy = y - yc;

                    if(ix >= matrix[0].length) {
                        ixiyReachEnd = true;
                        ixdyReachEnd = true;
                    }

                    if(iy >= matrix.length) {
                        ixiyReachEnd = true;
                        dxiyReachEnd = true;
                    }

                    if(dx < 0) {
                        dxdyReachEnd = true;
                        dxiyReachEnd = true;
                    }

                    if(dy < 0) {
                        dxdyReachEnd = true;
                        ixdyReachEnd = true;
                    }

                    if(!ixiyReachEnd) {
                        ixiytester.append(matrix[iy][ix]);
                        int test = Integer.parseInt(ixiytester.toString());
                        if(test > max && check(test)) max = test;
                    }

                    if(!dxiyReachEnd) {
                        dxiytester.append(matrix[iy][dx]);
                        int test = Integer.parseInt(dxiytester.toString());
                        if(test > max && check(test)) max = test;
                    }

                    if(!ixdyReachEnd) {
                        ixdytester.append(matrix[dy][ix]);
                        int test = Integer.parseInt(ixdytester.toString());
                        if(test > max && check(test)) max = test;
                    }

                    if(!dxdyReachEnd) {
                        dxdytester.append(matrix[dy][dx]);
                        int test = Integer.parseInt(dxdytester.toString());
                        if(test > max && check(test)) max = test;
                    }
                }
            }
        }
        return max;
    }

    private boolean check(int number) {
        double sqrt = Math.sqrt(number);
        return (sqrt - (int)sqrt) == 0;
    }
}

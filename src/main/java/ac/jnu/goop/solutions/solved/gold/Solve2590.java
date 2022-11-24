package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;

/**
 * Solved Gold IV
 * 색종이
 *
 * @since 2022.11.25 AM 02:21
 * @link https://www.acmicpc.net/problem/2590
 */
public class Solve2590 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력을 받아 종이의 개수를 저장한다.
        int[] papers = new int[7];
        for(int key = 1 ; key < papers.length ; key++) {
            int count = Integer.parseInt(br.readLine());
            papers[key] = count;
        }

        // 가장 큰 크기의 종이를 붙이고 남는 공간에 최대한 붙이는 방식으로 진행
        int answer = 0, remains;
        for(int size = 6 ; size > 0 ; ) {

            // 해당 사이즈에 붙여야 할 종이가 없다면 다음 사이즈로 넘어간다.
            if(papers[size] <= 0){
                size--;
                continue;
            }

            // 사용한 종이수를 늘리고, 남은 칸 수를 계산한다.
            answer++;
            papers[size]--;
            remains = 36 - (size*size);

            // 남은 칸 수에 최대한 색종이를 넣는다.
            for(int extraSize = 6-size ; extraSize > 0 && remains > 0 ; extraSize--) {
                if(papers[extraSize] <= 0) continue;

                int square = extraSize * extraSize;
                // 문제에서는 기본 종이 크기 3x3, 추가 종이 2x2에 일반화되지않는 예외가 있다.
                int maxPlace = (size == 3 && extraSize == 2)
                        ? (int) (remains / 4.5f) - 1
                        : remains / square;

                if (maxPlace > 0 && remains > square) {
                    int place = Math.min(maxPlace, papers[extraSize]);
                    papers[extraSize] -= place;
                    remains -= place * square;
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        br.close();
        bw.close();
    }
}

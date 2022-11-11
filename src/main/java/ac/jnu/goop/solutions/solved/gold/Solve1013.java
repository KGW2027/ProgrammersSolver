package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved Gold V
 * Contact
 *
 * @since 2022.11.10 PM 19:
 * @link https://www.acmicpc.net/problem/1013
 */
public class Solve1013 implements SolvedTestable {
    @Override
    public void solution() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int testcaseCount = Integer.parseInt(br.readLine());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for(int tc = 0 ; tc < testcaseCount ; tc++) {
                String input = br.readLine();
                bw.write(contact(input) ? "YES\n" : "NO\n");
            }

            bw.flush();
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean contact(String input) {
        // (100+1+ | 01)+
        // 100+1+ = 10 0*n 1*n
        // 01 = 01

        char[] inputArray = input.toCharArray();
        int validMode = 0;
        for(int key = 0 ; key < inputArray.length ; key++) {
            if (validMode == 0 && inputArray[key] == '0') { // (01)+ 검증
                if (++key < inputArray.length && inputArray[key] == '1') continue;
                return false;
            } else if (validMode == 0 && inputArray[key] == '1') { // (10 검증
                if (++key < inputArray.length && inputArray[key] == '0') {
                    validMode = 1;
                    continue;
                }
                return false;
            } else if (validMode == 1) { // (100 검증
                if (inputArray[key] == '0') validMode = 2;
                else return false;
            } else if (validMode == 2 && inputArray[key] == '1') { // (100+ 검증
                validMode = 3;
            } else if (validMode == 3) { // (100+1+) 검증
                if (inputArray[key] == '0') { // 01'0'이면 01검증, 11'0'0 이면 valid 2, 11'0'1 이면 valid 0
                    int plusKey = key + 1;
                    if (plusKey >= inputArray.length) return false; // 0으로 끝나는 경우는 존재하지 않음
                    if (inputArray[key - 2] == '1' && inputArray[plusKey] == '0') {
                        validMode = 2;
                        ++key;
                    } else if (inputArray[plusKey] == '1') {
                        validMode = 0;
                        ++key;
                    } else return false;
                }
            }
        }
        return validMode == 0 || validMode == 3;
    }
}

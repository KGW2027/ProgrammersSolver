package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;

/**
 * Solved Silver I
 * Casino
 * \r\n을 쓰면 출력 형식 오류가 난다는 사실을 알게 해준 문제
 *
 * @since 2022.11.17
 * @link https://www.acmicpc.net/problem/9351
 *
 */
public class Solve9351 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testcaseCount = Integer.parseInt(reader.readLine());
        String[] cases = new String[testcaseCount];
        for(int i = 0 ; i < testcaseCount ; i++) {
            cases[i] = reader.readLine();
        }
        reader.close();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int key = 0 ; key < cases.length ; key++) {
            writer.write(String.format("Case #%d:\n", key+1));
            String[] palindromes = findPalindrome(cases[key]);
            for(String p : palindromes) writer.write(p +"\n");
            writer.flush();
        }
    }

    private String[] findPalindrome(String text) {
        String[] palinds = new String[text.length()/2+1];
        int arrayKey = 0;

        char[] array = text.toCharArray();
        int maxLength = 1;

        for(int key = array.length-1 ; key >= 0 ; key--) {
            char prev = key > 0 ? array[key-1] : '.';
            char now = array[key];
            char next = key < array.length-1 ? array[key+1] : '*';

            if(now == next) {
                String even = palindFind(array, key, true);
                if(even.length() > maxLength) {
                    arrayKey = 0;
                    maxLength = even.length();
                    palinds[arrayKey++] = even;
                } else if(even.length() == maxLength) palinds[arrayKey++] = even;;
            }

            if(prev == next) {
                String odd = palindFind(array, key, false);
                if(odd.length() > maxLength) {
                    arrayKey = 0;
                    maxLength = odd.length();
                    palinds[arrayKey++] = odd;
                } else if(odd.length() == maxLength) palinds[arrayKey++] = odd;
            }
        }
        return Arrays.copyOf(palinds, arrayKey);
    }


    private String palindFind(char[] array, int key, boolean even) {
        int prevKey = key - 1;
        int nextKey = key + 1;
        if(even) nextKey++;

        while(prevKey >= 0 && nextKey < array.length && array[prevKey] == array[nextKey]) {
            prevKey--;
            nextKey++;
        }
        return charArrayToString(Arrays.copyOfRange(array, prevKey+1, nextKey));
    }

    private String charArrayToString(char[] array) {
        StringBuilder sb = new StringBuilder();
        for(char c : array) sb.append(c);
        return sb.toString();
    }
}



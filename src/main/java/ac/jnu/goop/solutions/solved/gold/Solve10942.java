package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Solved Gold IV
 * 팰린드롬?
 *
 * @since 2022.11.25 PM 12:38
 * @link https://www.acmicpc.net/problem/10942
 */
public class Solve10942 implements SolvedTestable {
    short[][] checked;
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        checked = new short[2][N];
        int[] seq = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int M = Integer.parseInt(reader.readLine());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int query = 0 ; query < M ; query++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            writer.write(isPalindrome(seq, Integer.parseInt(tokenizer.nextToken())-1, Integer.parseInt(tokenizer.nextToken())-1) + "\n");
        }
        writer.flush();
        writer.close();
    }

    private char isPalindrome(int[] seq, int start, int end) {
        int middle = (start+end)/2;
        int evenBonus = (end-start) % 2;

        short gap = checked[evenBonus][middle];
        if(gap > middle-start) return '1';

        for( ; gap <= middle-start ; gap++) if(seq[middle - gap] != seq[middle + gap + evenBonus]) return '0';
        checked[evenBonus][middle] = gap;
        return '1';
    }
}

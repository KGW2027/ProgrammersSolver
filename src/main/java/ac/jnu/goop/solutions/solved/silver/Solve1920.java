package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.util.Arrays;

/**
 * Solved Silver IV
 * 수 찾기
 *
 * @since 22.12.03 AM 01:00
 * @link https://www.acmicpc.net/problem/1920
 */
public class Solve1920 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int[] arr = new int[read()];
        int key = 0;
        while(key < arr.length) arr[key++] = read();
        Arrays.sort(arr);

        StringBuilder builder = new StringBuilder();
        int queries = read();
        while(queries-- > 0) builder.append(binSearch(arr, read())).append('\n');
        System.out.print(builder);
    }

    static int binSearch(int[] arr, int find){
        int left = 0, right = arr.length-1, center;
        while(left <= right) {
            center = (left+right)/2;
            if(arr[center] == find) return 1;
            else if(arr[center] < find) left = center+1;
            else right = center-1;
        }
        return 0;
    }

    static int read() throws Exception {
        int n = 0, b;
        boolean neg = false;
        while((b = System.in.read()) > 32){
            if(b == '-') neg = true;
            else n = n*10 + (b-'0');
        }
        return n * (neg ? -1 : 1);
    }
}

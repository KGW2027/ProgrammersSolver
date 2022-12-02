package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.util.Arrays;

/**
 * Solved Silver IV
 * 숫자 카드 2
 *
 * @since 22.12.03 AM 02:02
 * @link https://www.acmicpc.net/problem/10816
 */
public class Solve10816 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int N = read();
        int[] cards = new int[N];
        while(N-- > 0) cards[N] = read();
        Arrays.sort(cards);

        int M = read();
        StringBuilder str = new StringBuilder();
        while(M-- > 0) {
            int num = read(), find = binary_search(cards, num);
            if(find < 0 || find >= cards.length || cards[find] != num){
                str.append("0 ");
            } else {
                int end = binary_search_endpoint(cards, num);
                str.append(end-find+1).append(' ');
            }
        }
        System.out.print(str);

    }

    static int binary_search(int[] arr, int t) {
        int left = 0, right = arr.length-1, center;
        while(left <= right) {
            center = (left+right)/2;
            if(arr[center] < t) left = center+1;
            else right = center-1;
        }
        return left;
    }

    static int binary_search_endpoint(int[] arr, int t) {
        int left = 0, right = arr.length-1, center;
        while(left <= right) {
            center = (left+right)/2;
            if(arr[center] <= t) left = center+1;
            else right = center-1;
        }
        return right;
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

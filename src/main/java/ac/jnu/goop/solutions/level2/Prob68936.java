package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

/**
 * Programmers Level 2
 * 쿼드트리 압축
 *
 * @since 2022/11/04 AM 01:31
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/68936
 */
public class Prob68936 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((int[][]) args[0]);
    }

    public int[] solution(int[][] arr) {
        double log2 = Math.log10(arr.length) / Math.log10(2);
        int[] answer = compressQuadTree(arr, 0, 0, (int) log2);
        return answer;
    }

    private int[] compressQuadTree(int[][] arr, int sx, int sy, int size) {

        int[] result = new int[2];
        int maxKey = (int) Math.pow(2, size);
        if(maxKey > 1) {
            int equalsKey = arr[sy][sx];
            boolean breaked = false;

            for (int y = 0; y < maxKey; y++) {
                if (breaked) break;
                for (int x = 0; x < maxKey; x++) {

                    int rx = x + sx, ry = y + sy;
                    if (arr[ry][rx] != equalsKey) {
                        breaked = true;
                        break;
                    }
                }
            }

            if (breaked) { // 압축실패, 4분할 쿼드압축
                int half = maxKey/2;
                int[] lu = compressQuadTree(arr, sx, sy, size-1);
                int[] ru = compressQuadTree(arr, sx+half, sy, size-1);
                int[] ld = compressQuadTree(arr, sx, sy+half, size-1);
                int[] rd = compressQuadTree(arr, sx+half, sy+half, size-1);
                result[0] = lu[0] + ru[0] + ld[0] + rd[0];
                result[1] = lu[1] + ru[1] + ld[1] + rd[1];
            } else { // 압축 성공
                result[equalsKey] = 1;
            }
        } else {
            int value = arr[sy][sx];
            result[value] = 1;
        }

        return result;
    }
}

package ac.jnu.goop.solutions.solved.platinum;

import ac.jnu.goop.SolvedTestable;
/*
5 5 반례
00100
01000
10101
01000
00100
 */
/**
 * Solved Platinum V
 * 다이아몬드 광산
 *
 * @since 22.12.01 AM 05:58
 * @link https://www.acmicpc.net/problem/1028
 */
public class Solve1028 implements SolvedTestable {
    @Override
    public void solution() throws Exception {

        short R = read(), C = read();
        boolean[][] matrix = new boolean[R][C];
        short[][] ldru = new short[R][C];
        short[][] rdlu = new short[R][C];
        int s = 0;
        int maxSize = 0;
        while(s < R*C) {
            byte b = (byte) System.in.read();
            if(b<=32) continue;
            short y = (short) (s/C), x = (short) (s%C);
            matrix[y][x] = (b == '1');
            if(matrix[y][x] && maxSize == 0) maxSize = 1;
            if(matrix[y][x] && y > 0) {
                if(x > 0 && matrix[y-1][x-1]) rdlu[y][x] = (short) (rdlu[y-1][x-1]+1);
                if(x < (C-1) && matrix[y-1][x+1]) ldru[y][x] = (short) (ldru[y-1][x+1]+1);
            }
            s++;
        }
        if(maxSize == 1) {
            for (int y = 0; y < R-maxSize; y++) {
                for (int x = 0; x < C-maxSize; x++) {
                    for(int size = ldru[y][x] ; size >= maxSize ; size--) {
                        int tx = x + (size * 2);
                        if(tx < C && rdlu[y][tx] >= size) {
                            int ty = y + size;
                            tx = (x + tx)/2;
                            if(ty < R && ldru[ty][tx] >= size && rdlu[ty][tx] >= size) maxSize = size+1;
                        }
                    }
                }
            }
        }
        System.out.print(maxSize);
    }

    static short read() throws Exception {
        short n = 0, b;
        while((b = (short)System.in.read()) > 32) n = (short) (n*10+(b-'0'));
        return n;
    }

//    static boolean[][] matrix;
//    @Override
//    public void solution() throws Exception {
//        short R = read(), C = read();
//        matrix = new boolean[R][C];
//        int slot = 0;
//        while(slot < R*C) {
//            byte b = (byte) System.in.read();
//            if(b <= 32) continue;
//            matrix[slot/C][slot%C] = (b == '1');
//            slot++;
//        }
//
//        short maxSize = 0;
//        for(short r = 0 ; r < R-maxSize ; r++) {
//            for(short c = 0 ; c < C-maxSize ; c++) {
//                if(matrix[r][c]) {
//                    short size = test(c, r, 1, true);
//                    if(size > maxSize) maxSize = size;
//                }
//            }
//        }
//
//        System.out.print(maxSize);
//    }
//
//    static short test(int x, int y, int len, boolean open) {
//        try {
//            if (open) {
//                if (matrix[y + len][x - len] && matrix[y + len][x + len]) {
//                    short next = test(x, y, len + 1, true);
//                    short close = test(x, y+len, len, false);
//                    return (short) Math.max(next, close);
//                }
//                return 1;
//            } else {
//                for (int off = 1 ;  off <= len ; off++) {
//                    int gap = len - off;
//                    if (!matrix[y + off][x - gap] || !matrix[y + off][x + gap]) return 1;
//                }
//                return (short) (len+1);
//            }
//        }catch(ArrayIndexOutOfBoundsException ex){return 1;}
//    }
//

}

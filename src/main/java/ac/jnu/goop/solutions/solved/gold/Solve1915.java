package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

/**
 * Solved Gold IV
 * 가장 큰 정사각형
 *
 * @since 22.11.29 AM 02:08
 * @link https://www.acmicpc.net/problem/1915
 */
public class Solve1915 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        short line = 0, key = 0, cont = 0;
        short[][] map = new short[read()][read()];
        while(line < map.length) {
            byte b = (byte) System.in.read();
            if(b == '\n') {
                line++; key = 0; cont = 0; continue;
            }
            switch(b){
                case '0':
                    cont = 0;
                    break;
                case '1':
                    cont++;
                    break;
            }
            map[line][key++] = cont;
        }

        int maxSize = 0;
        for(int height = 0 ; height < map.length ; height++) {
            for(int x = map[height].length-1 ; x >= 0 ; x--) {
                if(map[height][x] > maxSize) {
                    int suc = Math.max(upTest(map, x, height, maxSize), downTest(map, x, height, maxSize));
                    if(suc > maxSize) maxSize = suc;
                }
            }
        }
        System.out.print(maxSize*maxSize);
    }

    static int upTest(short[][] map, int sx, int sy, int least) {
        int up = 1, test = map[sy][sx];
        for( ; up < test && sy+up < map.length ; up++) {
            if(map[sy+up][sx] < test) test = map[sy+up][sx];
            if(test <= least) return 0;
        }
        return Math.min(up, test);
    }

    static int downTest(short[][] map, int sx, int sy, int least) {
        int down = 1, test = map[sy][sx];
        for( ; down < test && sy-down >= 0; down++) {
            if(map[sy-down][sx] < test) test = map[sy-down][sx];
            if(test <= least) return 0;
        }
        return Math.min(down, test);
    }

    static int read() throws Exception {
        int b,n=0;
        while((b=System.in.read())>32) n=n*10+(b-'0');
        return n;
    }
}

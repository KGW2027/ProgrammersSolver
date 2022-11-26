package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Solved Gold IV
 * 별 찍기 - 11
 *
 * @since 22.11.26 PM 21:11
 * @link https://www.acmicpc.net/problem/2448
 */
public class Solve2448 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        short N = Short.parseShort(reader.readLine());
        boolean[][] map = new boolean[N][N << 1];

        draw(map, N,0,N-1);

        BufferedOutputStream bos = new BufferedOutputStream(System.out);
        for(boolean[] line : map){
            for(boolean star : line)
                bos.write(star ? '*' : ' ');
            bos.write('\n');
        }
        bos.flush();
    }

    void draw(boolean[][] map, short size, int offX, int offY) {
        if(size == 3) {
            map[offY-2][offX+2] = true;
            map[offY-1][offX+1] = map[offY-1][offX+3] = true;
            for(int addX = 0 ; addX < 5 ; addX++) map[offY][offX+addX] = true;
        } else {
            short unitY = (short) (size/2);
            draw(map, unitY, offX, offY);
            draw(map, unitY, offX+size, offY);
            draw(map, unitY, offX+(size >> 1), offY-unitY);
        }
    }
}

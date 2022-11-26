package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved Gold V
 * 별 찍기 - 10
 *
 * @since 22.11.26 PM 20:38
 * @link https://www.acmicpc.net/problem/2447
 */
public class Solve2447 implements SolvedTestable {

    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        short N = Short.parseShort(reader.readLine());
        boolean[][] stars = new boolean[N][N];
        draw(stars, N, 0, 0);

        BufferedOutputStream bos = new BufferedOutputStream(System.out);
        for(boolean[] line : stars) {
            for (boolean star : line)
                bos.write(star ? '*' : ' ');
            bos.write('\n');
        }
        bos.flush();
    }

    private void draw(boolean[][] stars, short size, int offX, int offY) {
        if(size == 0) {
            stars[offY][offX] = true;
        } else {
            short unit = (short) (size/3);
            for(int callDraw = 0 ; callDraw < 9 ; callDraw++) {
                if(callDraw!=4) draw(stars, unit, offX + unit*(callDraw%3), offY + unit*(callDraw/3));
            }
        }
    }
}

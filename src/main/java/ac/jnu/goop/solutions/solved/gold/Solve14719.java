package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;

/**
 * Solved Gold V
 * 빗물
 *
 * @since 2022.11.16 AM 12:43
 * @link https://www.acmicpc.net/problem/14719
 */
public class Solve14719 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        String[] heights = br.readLine().split(" ");
        br.close();

        int height = Integer.parseInt(size[0]), width = Integer.parseInt(size[1]), h;
        char[][] cmap = new char[width][height];
        for(int key = 0 ; key < width ; key++) {
            h = Integer.parseInt(heights[key]);
            for(int x = 0 ; x < height ; x++) cmap[key][x] = x < h ? '*' : '.';
        }

        int water = 0, buffer;
        boolean searching;
        for (int searchHeight = height - 1; searchHeight >= 0; searchHeight--) {
            buffer = 0;
            searching = false;

            for (int searchWidth = 0; searchWidth < width; searchWidth++) {
                char c = cmap[searchWidth][searchHeight];
                if(c == '*') {
                    if(!searching) searching = true;
                    else if(buffer > 0) {
                        water += buffer;
                        buffer = 0;
                    }
                } else if(searching) buffer++;
            }
        }

        for(char[] c : cmap) System.out.println(Arrays.toString(c));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(water));
        bw.flush();
        bw.close();
    }
}

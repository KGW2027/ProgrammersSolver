package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved.ac Silver I
 * 최대 공약수
 *
 * @since 2022.11.14 PM 16:55
 * @link https://www.acmicpc.net/problem/1850
 */
public class Solve1850 implements SolvedTestable {
    @Override
    public void solution() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            br.close();
            long a = Long.parseLong(line[0]);
            long b = Long.parseLong(line[1]);

            long count = euclid(Math.max(a, b), Math.min(a, b));

            BufferedOutputStream bos = new BufferedOutputStream(System.out);
            while (count-- > 0) bos.write('1');
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long euclid(long x, long s) {
        long mod = x % s;
        return mod == 0
                ? s
                : euclid(s, mod);
    }
}

package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Solved Gold IV
 * 결! 합!
 *
 * @since 2022.11.18 AM 01:02
 * @link https://www.acmicpc.net/problem/16722
 */
public class Solve16722 implements SolvedTestable {

    // 모두 같을 경우 OR 연산의 값이 같다
    // 모두 다를 경우 OR 연산의 값이 8(<< x)-1이다.
    enum CardStatus {
        CIRCLE(1),
        TRIANGLE(2),
        SQUARE(4),

        YELLOW(1 << 8),
        RED(2 << 8),
        BLUE(4 << 8),

        GRAY(1 << 16),
        WHITE(2 << 16),
        BLACK(4 << 16)
        ;

        int flag;
        CardStatus(int i) { flag = i; }
    }

    @Override
    public void solution() throws IOException {
        int[] cards = new int[9];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int card = 0 ; card < cards.length ; card++) {
            String[] stat = br.readLine().split(" ");
            cards[card] = CardStatus.valueOf(stat[0]).flag + CardStatus.valueOf(stat[1]).flag + CardStatus.valueOf(stat[2]).flag;
        }

        List<Integer> haps = new ArrayList<>();
        for(int c1 = 0 ; c1 <= 6 ; c1++) {
            for(int c2 = c1+1 ; c2 <= 7 ; c2++) {
                for(int c3 = c2+1 ; c3 <= 8 ; c3++) {
                    if(isHap(cards[c1], cards[c2], cards[c3])) haps.add(cards[c1] | cards[c2] | cards[c3]);
                }
            }
        }

        int round = Integer.parseInt(br.readLine()), score = 0;
        boolean gyeol = false;
        for(int r = 0 ; r < round ; r++) {
            String[] cmd = br.readLine().split(" ");
            if(cmd[0].equals("G")) {
                if(haps.size() == 0 && !gyeol){
                    score += 3;
                    gyeol = true;
                }
                else score -= 1;
            } else {
                int sum = cards[Integer.parseInt(cmd[1])-1] | cards[Integer.parseInt(cmd[2])-1] | cards[Integer.parseInt(cmd[3])-1];
                if(haps.contains(sum)) {
                    haps.remove((Object) sum);
                    score++;
                } else {
                    score--;
                }
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(score));
        bw.flush();
        bw.close();
    }

    private boolean isHap(int c1, int c2, int c3) {

        int test = c1 | c2 | c3;

        int shape = test & 0x000000FF;
        int foreground = (test & 0x0000FF00) >> 8;
        int background = (test & 0x00FF0000) >> 16;

        return (shape == (c1 & 0x000000FF) || shape == 7)
                &&  (foreground == (c1 & 0x0000FF00) >> 8 || foreground == 7)
                &&  (background == (c1 & 0x00FF0000) >> 16 || background == 7);
    }
}

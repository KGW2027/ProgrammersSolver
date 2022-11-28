package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.util.Arrays;

/**
 * Solved Gold III
 * 과제
 *
 * @since 22.11.29 AM 1:26
 * @link https://www.acmicpc.net/problem/13904
 */
public class Solve13904 implements SolvedTestable {
    @Override
    public void solution() throws Exception {
        int N = read();
        short[][] dp = new short[N][];
        int mxd = 0;
        while(N-- > 0){
            dp[N] = new short[]{read(), read()};
            if(dp[N][0] > mxd) mxd = dp[N][0];
        }
        boolean[] taskReserved = new boolean[mxd];
        int score = 0;
        Arrays.sort(dp, (o1, o2) -> Short.compare(o2[1], o1[1]));
        for(short[] task : dp) {
            for(int tasksKey = task[0]-1 ; tasksKey >= 0 ; tasksKey--) {
                if(!taskReserved[tasksKey]) {
                    taskReserved[tasksKey] = true;
                    score += task[1];
                    break;
                }
            }
        }
        System.out.print(score);
    }

    static short read() throws Exception {
        short b, n = 0;
        while((b = (short) System.in.read()) > 32) n = (short) (n*10 + (b-'0'));
        return n;
    }
}

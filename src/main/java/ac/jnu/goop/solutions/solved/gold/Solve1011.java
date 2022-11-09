package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Solved Gold V
 * Fly me to the Alpha Centauri
 *
 * @since 2022.11.09 PM
 * @link https://www.acmicpc.net/problem/1011
 */
public class Solve1011 implements SolvedTestable {

    // 속도 1은 0->1 :(1^1)
    // 속도 2는 0->1->3->4 (2^2)
    // 속도 3은 0->1->3->6->8->9 (3^2)
    // 속도 4는 0->1->3->6->10->13->15->16 (4^2)
    // 속도 5는 0->1->3->6->10->15->19->22->24->25 (5^2)
    // => 거리가 n^2보다 낮으면 최대속도 n-1까지만 가속후 내린다.
    @Override
    public void solution() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int count = Integer.parseInt(br.readLine());
            for(int i = 0 ; i < count ; i++) {
                System.out.println(calc(br.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calc(String test) {

        String[] split = test.split(" ");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int dist = y - x;
        int maxSpeed = (int) Math.sqrt(dist);

        int speed = 1;
        int moveCount = 0;
        int stopRange = stopRange(maxSpeed);
        int calcEndDist = y - stopRange;

        while (x < calcEndDist) { // 최소 감속 필요거리까지 전진
            x += speed == maxSpeed ? speed : speed++;
            moveCount++;
        }

        // stopRange와 남은 거리가 같으면 현재속도부터 1씩 줄여가며 감속, 더 낮으면 현재속도-1부터 감속
        if ((y - x) == stopRange) {
            x += stopRange;
            moveCount += speed;
        } else {
            x += stopRange - speed;
            moveCount += speed - 1;
        }

        if (x != y) moveCount += 1;
        return moveCount;
    }

    private int stopRange(int i) {
        return i <= 1 ? 1 : i + stopRange(i-1);
    }
}

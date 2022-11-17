package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;

/**
 * Solved Gold I
 * Coloring Graph
 *
 * @since 2022.11.17 PM
 * @link https://www.acmicpc.net/problem/24782
 */
public class Solve24782 implements SolvedTestable {

    int colorCount;

    @Override
    public void solution() throws IOException {
        // 1. 입력 받기
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        String[][] connections = new String[nodes][];

        for(int node = 0 ; node < nodes; node++) {
            String[] split = reader.readLine().split(" ");
            connections[node] = Arrays.copyOf(split, split.length+1);
            connections[node][split.length] = String.valueOf(node);
        }
        reader.close();

        String[][] sortedConnections = connections.clone();
        Arrays.sort(sortedConnections, (o1, o2) -> {
                    if(o1.length > o2.length) return 1;
                    else if(o1.length < o2.length) return -1;
                    return 0;
                });

        int min = Integer.MAX_VALUE;

        // 모든 노드에서 출발하는 경우의 수를 검사한다.
        for(int i = 0 ; i < nodes ; i++) {
            int[] colorMap = new int[nodes];
            colorCount = 0;

            String[] maxConnect = sortedConnections[i];
            int idx = Integer.parseInt(sortedConnections[i][maxConnect.length - 1]);
            track(colorMap, connections, idx);
            min = Math.min(colorCount, min);
        }

        // 색 중에 최대 숫자가 색깔의 개수이다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private void track(int[] colorMap, String[][] connects, int main) {
        String[] connections = connects[main];

        boolean[] usingColorCheck = new boolean[colorCount];
        if (usingColorCheck.length > 0) {
            for (int i = 0; i < connections.length; i++) {
                int index = Integer.parseInt(connections[i]);
                int idxColor = colorMap[index];
                if (idxColor == 0) continue; // 색이 없는 경우는 스킵
                usingColorCheck[idxColor - 1] = true; // 사용 중인 색은 사용중 표시
            }
        }

        int ct = 0;
        for (; ct < usingColorCheck.length; ct++) {
            if (!usingColorCheck[ct]) break;
        }
        ct++;

        colorMap[main] = ct;
        if (ct > colorCount) colorCount = ct;

        for (int key = 0; key < connections.length - 1; key++) {
            int idx = Integer.parseInt(connections[key]);
            if(colorMap[idx] == 0) track(colorMap, connects, Integer.parseInt(connections[key]));
        }
    }
}

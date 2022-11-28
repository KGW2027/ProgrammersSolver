package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Solved Gold IV
 * 트리
 *
 * @since 2022.11.28 PM 19:01
 * @link https://www.acmicpc.net/problem/4803
 */
public class Solve4803 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int c1 = read(), c2 = read();
        int caseNum = 0;
        StringBuilder builder = new StringBuilder();
        while(c1 != 0 || c2 != 0) {
            caseNum++;
            int[] union = new int[c1+1];
            boolean[] cycleParent = new boolean[c1+1];
            boolean printed = false;
            for(; c2 > 0 ; c2--) {
                int e1 = read(), e2 = read();
                if(printed) continue;
                int e1p = getParent(union, e1), e2p = getParent(union, e2);

                // 사이클이 있는 트리만 제거하고 개수를 세야한다.
                if(e1p == e2p){
                    cycleParent[e1p] = true;
                    continue;
                }

                if(union[e2] == 0) union[e2] = e1;
                else{
                    reverse(union, e2);
                    union[e2] = e1;
                }
            }

            int trees = 0;
            for (int key = 1; key < union.length; key++) if (union[key] == 0) trees++;
            boolean[] cycleFinalFind = new boolean[c1+1];
            for(int key = 0 ; key < cycleParent.length ; key++) {
                if(cycleParent[key]) {
                    int parent = getParent(union, key);
                    if(!cycleFinalFind[parent]) {
                        cycleFinalFind[parent] = true;
                        trees--;
                    }
                }
            }

            builder.setLength(0);
            builder.append("Case ").append(caseNum).append(": ");
            if (trees == 0) builder.append("No trees.\n");
            else if (trees == 1)  builder.append("There is one tree.\n");
            else builder.append("A forest of ").append(trees).append(" trees.\n");
            writer.write(builder.toString());

            c1 = read();
            c2 = read();
        }
        writer.flush();
    }

    static int reverse(int[] array, int key) {
        int parent = array[key];
        if(parent > 0) array[reverse(array, parent)] = key;
        return key;
    }

    static int getParent(int[] array, int target) {
        if(array[target] == 0) return target;
        return getParent(array, array[target]);
    }

    static int read() throws IOException {
        int num = 0;
        for(int b = System.in.read() ; b != 10 && b != 13 && b != 32 ; b = System.in.read())
            num = num*10 + (b-'0');
        return num;
    }
}

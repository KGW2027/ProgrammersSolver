package ac.jnu.goop.solutions.solved.gold;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

/**
 * Solved Gold I
 * 구간 합 구하기
 *
 * @since 22.11.28 PM 21:32
 * @link https://www.acmicpc.net/problem/2042
 */
public class Solve2042 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = readInt(), M = readInt(), K = readInt(), lgN = lg(N), queries = M+K;
        int offset = 1 << lgN;
        long[] segTree = new long[1 << (lgN+1)];

        int key = 0;
        while(N-- > 0) {
            int cur = offset+(key++);
            segTree[cur] = readLong();
            for(int parent = cur/2 ; parent >= 1 ; parent /= 2) segTree[parent] += segTree[cur];
        }

        while(queries-- > 0) {
            if(readInt() == 1) {
                int loc = offset+readInt()-1;
                long before = segTree[loc];
                segTree[loc] = readLong();
                for(int parent = loc/2 ; parent >= 1 ; parent /= 2) segTree[parent] += segTree[loc] - before;
            } else {
                writer.write(sum(segTree, readInt()-1, readInt()-1, 0, offset) + "\n");
            }
        }
        writer.flush();
    }

    static int lg(long n) {
        return (int) Math.ceil(Math.log(n) / Math.log(2));
    }

    static long sum(long[] tree, int start, int end, int offset, int range) {
        int leapStart = tree.length / 2, halfRange = range / 2;

        if(halfRange == 0) return tree[leapStart+offset]; // 단말 노드까지 온 경우, 단말 노드의 값을 반환한다.
        else if(end < offset || offset + range < start) return 0; // 겹치는 범위가 없을 경우 탐색하지 않는다.
        else if (start <= offset && offset + range <= end)  return tree[(leapStart + offset) / range]; // 부분합 노드가 범위 안에 속한경우

        long sum = 0;
        if (offset + halfRange <= end) sum += sum(tree, start, end, offset + halfRange, halfRange); // 오른쪽 탐색
        if (start < offset + halfRange) sum += sum(tree, start, end, offset, halfRange); // 왼쪽 탐색
        return sum;
    }

    static int readInt() throws IOException {
        int n = 0;
        boolean neg = false;
        for(int b = System.in.read() ; b != 10 && b != 13 && b != 32 ; b = System.in.read()) {
            if(b == '-') neg = true;
            else n = n*10 + (b-'0');
        }
        return neg ? n * -1 : n;
    }

    static long readLong() throws IOException {
        long n = 0;
        boolean neg = false;
        for(int b = System.in.read() ; b != 10 && b != 13 && b != 32 ; b = System.in.read()) {
            if(b == '-') neg = true;
            else n = n*10 + (b-'0');
        }
        return neg ? n * -1 : n;
    }
}

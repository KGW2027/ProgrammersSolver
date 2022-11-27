package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Solve1927 implements SolvedTestable {

    static int[] heap;
    static int length;
    @Override
    public void solution() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = read();
        heap = new int[depth(N)];
        length = 1;
        while(N-- > 0) {
            int read = read();
            if(read == 0) {
                if(heap[1] == 0) writer.write("0");
                else writer.write(String.valueOf(pop()));
                writer.write('\n');
            } else {
                push(read);
            }
        }
        writer.flush();
    }

    static int depth(int log) {
        double lg = Math.log(log) / Math.log(2);
        return (int) Math.pow(2, Math.ceil(lg));
    }

    static void swap(int k1, int k2) {
        int buffer = heap[k1];
        heap[k1] = heap[k2];
        heap[k2] = buffer;
    }

    static void compareTopdown(int k1) {
        if(k1*2 > length) return;

        int k1m2 = heap[k1*2] == 0 ? Integer.MAX_VALUE : heap[k1*2];
        int k1m2p1 = heap[k1*2+1] == 0 ? Integer.MAX_VALUE : heap[k1*2+1];
        int min = Math.min(Math.min(heap[k1], k1m2), k1m2p1);
        if(min == heap[k1*2]) {
            swap(k1, k1*2);
            compareTopdown(k1*2);
        } else if (min == heap[k1*2+1]) {
            swap(k1, k1*2+1);
            compareTopdown(k1*2+1);
        }
    }

    static void compareBottomUp(int k1, int k2){
        if(k1 == 1) return;
        if(heap[k1] < heap[k2]) {
            swap(k1, k2);
            compareBottomUp(k2, k2/2);
        }
    }

    static int pop() {
        int val = heap[1];
        heap[1] = heap[--length];
        heap[length] = 0;
        compareTopdown(1);
        return val;
    }

    static void push(int val) {
        heap[length] = val;
        compareBottomUp(length, length/2);
        length++;
    }

    static int read() throws IOException {
        int num = 0;
        for(byte b = (byte) System.in.read(); b != 10 && b != 13 && b != 32 ; b = (byte) System.in.read())
            num = num*10 + (b-'0');
        return num;
    }
}

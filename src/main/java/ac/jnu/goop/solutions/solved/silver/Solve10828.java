package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.io.*;
import java.util.Arrays;

public class Solve10828 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine());
        String[] stack = new String[N];
        int stackPointer = 0;
        while(N-- > 0) {
            String cmd = reader.readLine();
            int sp = cmd.indexOf(' ');

            if(sp > 0) {
                if(stack.length == stackPointer) stack = Arrays.copyOf(stack, stack.length+1);
                stack[stackPointer++] = cmd.substring(sp+1);
                continue;
            }

            switch(cmd) {
                case "pop":
                    writer.write(stackPointer == 0 ? "-1" : stack[--stackPointer]);
                    break;
                case "size":
                    writer.write(String.valueOf(stackPointer));
                    break;
                case "empty":
                    writer.write(stackPointer == 0 ? "1" : "0");
                    break;
                case "top":
                    writer.write(stackPointer == 0 ? "-1" : stack[stackPointer-1]);
                    break;
            }
            writer.write('\n');
        }
        writer.flush();
    }
}

package ac.jnu.goop.solutions.solved.silver;

import ac.jnu.goop.SolvedTestable;

import java.io.*;

public class Solve10845 implements SolvedTestable {
    @Override
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(reader.readLine()), pop = 0, push = 0;
        String[] queue = new String[N];

        while(N-- > 0) {
            String command = reader.readLine();
            int space = command.indexOf(' ');
            if(space >= 0) {
                queue[push++] = command.substring(space+1);
                continue;
            }

            switch (command) {
                case "pop":
                    writer.write(queue[pop] == null ? "-1" : queue[pop++]);
                    break;
                case "size":
                    writer.write(String.valueOf(push-pop));
                    break;
                case "empty":
                    writer.write((push == pop) ? "1" : "0");
                    break;
                case "front":
                    writer.write(queue[pop] == null ? "-1" : queue[pop]);
                    break;
                case "back":
                    writer.write((pop == push) ? "-1" : queue[push-1]);
                    break;
            }
            writer.write('\n');
        }
        writer.flush();

    }
}

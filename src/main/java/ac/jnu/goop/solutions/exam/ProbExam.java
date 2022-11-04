package ac.jnu.goop.solutions.exam;

import ac.jnu.goop.Testable;

import java.util.*;

public class ProbExam implements Testable {

    @Override
    public Object solution(Object... args) {
//         return solution((int) args[0], (int[]) args[1], (int) args[2]);
        return solution((int[]) args[0], (int[][]) args[1]);
    }





}

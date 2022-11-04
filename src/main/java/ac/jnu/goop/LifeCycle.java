package ac.jnu.goop;

import ac.jnu.goop.solutions.exam.ProbExam;
import ac.jnu.goop.solutions.level2.*;

public class LifeCycle {
    public static void main(String[] args) {
//        print(new TestcaseExecutor(new ProbExam())
//                .addTestCase(4, 25, new int[]{2, 14, 11, 21, 17}, 3)
//                .test());
//
//        print(new TestcaseExecutor(new ProbExam())
//                .addTestCase(4, 25, new int[]{2, 13, 15, 17, 23}, 3)
//                .test());

        print(new TestcaseExecutor(new ProbExam())
                .addTestCase(44, new int[]{14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
                        new int[][]{{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}})
                .test());

    }

    private static void test134239() {
        TestcaseExecutor te = new TestcaseExecutor(new Prob134239());
        te.addTestCase(new double[]{33.0, 31.5, 0.0, -1.0}, 5, new int[][]{{0, 0}, {0, -1}, {2, -3}, {3, -3}});
        print(te.test());
    }


    // 3 x n 타일링 문제 ( 실패함 )
    private static void test12902() {
        TestcaseExecutor te = new TestcaseExecutor(new Prob12902());
//        te.addTestCase(3, 2);
//        te.addTestCase(11, 4);
//        te.addTestCase(41, 6);
//        te.addTestCase(153, 8);
//        te.addTestCase(658712818, 5000);
        print(te.test());
    }


    // 택배 상자 문제
    private static void test131704() {
        TestcaseExecutor te = new TestcaseExecutor(new Prob131704());
        te.addTestCase(2, new int[]{4, 3, 1, 2, 5});
        te.addTestCase(5, new int[]{5, 4, 3, 2, 1});
        print(te.test());
    }
    private static void print(Object o) {
        System.out.println(o);
    }
}

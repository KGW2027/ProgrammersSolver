package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.Testable;

/**
 * Programmers Lv 2
 * 3 x n 타일링
 * 직접 풀기에 실패함
 *
 *
 * @since 2022.11.03 PM
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12902
 * 예제코드
 * @link https://www.geeksforgeeks.org/tiling-with-dominoes/
 */
public class Prob12902 implements Testable {

    public int solution(int n) {
        return (int) (countWays(n) % (int)(Math.pow(10, 9)+7));
    }

    private long countWays(int n) {
        long []A = new long[n+1];
        long []B = new long[n+1];
        A[0] = 1; A[1] = 0;
        B[0] = 0; B[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            A[i] = A[i - 2] + 2 * B[i - 1];
            B[i] = A[i - 1] + B[i - 2];
        }

        return A[n];
    }

//    private long getSmallCases(int size) {
//        tab += "\t";
//
//        // 만약 이미 구한 값이라면 빠르게 반환한다.
//        if(groupsPostCalcs[size] > 0){
//            tab = tab.substring(0, tab.length()-1);
//            return groupsPostCalcs[size];
//        }
//        System.out.println(tab+"[Request] " + size);
//
//        long value = 0;
//        for(int groupSize = size ; groupSize > 1 ; groupSize--) {
//            System.out.println(tab + "[Test] " + groupSize);
//            tab += "\t";
//
//            int lastSplit = size-groupSize;
//
//            for(int splitStart = 0 ; splitStart <= lastSplit ; splitStart++) {
//                long leftValue = getSmallCases(splitStart) % mod;
//                long rightValue = getSmallCases(size - (splitStart+groupSize)) % mod;
//                long cases = ((leftValue * rightValue) << 1) % mod;
//                System.out.printf(tab+"[%d ~ %d] * %d * [%d ~ %d] = %d \n", 0, splitStart, 2, splitStart+groupSize, size, cases);
//                value += cases;
//            }
//
//            value %= mod;
//            tab = tab.substring(0, tab.length()-1);
//        }
//
//        if(size > 1) {
//            long pow = 1;
//            for (int i = 0; i < size; i++) {
//                pow *= 3;
//                pow = pow % mod;
//            }
//            // 그룹이 하나도 없는 경우 ( pow )
//            value += pow;
//            System.out.println(tab+"[Pow] : " + pow);
//            value %= mod;
//        }
//
//        groupsPostCalcs[size] = value;
//        tab = tab.substring(0, tab.length()-1);
//        System.out.println(tab + "[Result] " + size + " => " + value);
//        return value;
//    }

//
//    private long getSmallCases(int size) {
//
//        if(groupsPostCalcs[size] > 0){
//            return groupsPostCalcs[size];
//        }
//
//        long count = 0;
//        for(int groups = size ; groups > 1 ; groups--) {
//            for(int start = 0 ; start <= size - groups ; start++) {
//                long left = getSmallCases(start);
//                long right = getSmallCases(size-(start+groups));
//                long result = (left * right) << 1;
//                count += result % mod;
//            }
//            count = count % mod;
//        }
//        groupsPostCalcs[size] = count;
//        return count;
//    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0]);
    }
}

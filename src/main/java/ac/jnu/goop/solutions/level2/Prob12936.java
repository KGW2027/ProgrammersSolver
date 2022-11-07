package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

/**
 * 프로그래머즈 Lv2
 * 줄 서는 방법
 *
 * @since 2022.11.07 PM 17:
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/12936
 */
public class Prob12936 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {3, 6L, new int[]{3, 1, 2}}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (long) args[1]);
    }

    boolean[] peoples;
    public int[] solution(int n, long k) {
        peoples = new boolean[n+1];
        long[] facts = new long[n];
        factorial(facts, n);

        int[] answer = new int[n];
        int c = 1;
        for( ; k > 0 ; c++) {
            long fact = facts[n-c-1];
            int z = (int) (k/fact);
            answer[c - 1] = findPerson(k%fact == 0 ? z : z+1);
            k -= z * fact;
        }

        for(int last = peoples.length-1 ; last > 0 ; last--) {
            if(!peoples[last]) answer[(c++)-1] = last;
        }

        return answer;
    }

    public long factorial(long[] fact, int n) {
        fact[n-1] = (n == 1) ? 1 : n * factorial(fact,n-1);
        return fact[n-1];
    }

    public int findPerson(int great) {
        int count = 0, k = 1;
        for(; k < peoples.length ; k++) {
            if(peoples[k]) continue;
            if(++count == great) {
                peoples[k] = true;
                return k;
            }
        }
        return 0;
    }


}

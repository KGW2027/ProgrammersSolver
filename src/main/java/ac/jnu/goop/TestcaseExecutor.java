package ac.jnu.goop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TestcaseExecutor {

    Testable instance;
    HashMap<List<Object>, Object> testcase;

    public TestcaseExecutor(Testable instance) {
        this.instance = instance;
        testcase = new HashMap<>();
    }

    public TestcaseExecutor addTestCase(Object result, Object... args) {
        List<Object> input = new ArrayList<>(Arrays.asList(args));
        testcase.put(input, result);
        return this;
    }

    public boolean test() {
        for(List<Object> input : testcase.keySet()) {
            Object result = instance.solution(input.toArray());
            if(!resultEquals(result, testcase.get(input))) return false;
        }
        return true;
    }

    private boolean resultEquals(Object result1, Object result2) {
        if(result1.getClass().isArray()) {
            return arrayCompare(result1, result2);
        } else {
            return result1.equals(result2);
        }
    }

    static class ArrayCompares <T> {

        T[] array1, array2;

        ArrayCompares(T[] a1, T[] a2) {
            array1 = a1;
            array2 = a2;
        }

        boolean compare() {
            for(int i= 0 ; i < array1.length ; i++) {
                if(!array1[i].equals(array2[i])) return false;
            }
            return true;
        }
    }

    private boolean arrayCompare(Object a1, Object a2) {
        if(a1 instanceof double[]) {
            double[] d1 = (double[]) a1;
            Double[] D1 = Arrays.stream(d1).boxed().toArray(Double[]::new);

            double[] d2 = (double[]) a2;
            Double[] D2 = Arrays.stream(d2).boxed().toArray(Double[]::new);

            return new ArrayCompares<Double>(D1, D2).compare();
        } else if (a1 instanceof int[]) {
            int[] i1 = (int[]) a1;
            Integer[] I1 = Arrays.stream(i1).boxed().toArray(Integer[]::new);

            int[] i2 = (int[]) a2;
            Integer[] I2 = Arrays.stream(i2).boxed().toArray(Integer[]::new);

            return new ArrayCompares<Integer>(I1, I2).compare();
        } else if (a1 instanceof String[]) {
            return new ArrayCompares<String>((String[]) a1, (String[])a2).compare();
        }
        return false;
    }
}

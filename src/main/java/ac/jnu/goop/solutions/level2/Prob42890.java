package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.ArrayList;
import java.util.List;

/**
 * Programmers Level 2
 * 후보키
 *
 * @since 2022.11.05 PM 16:42
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42890
 */
public class Prob42890 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}, 2},
                {new String[][]{ {"a","1","aaa","c","ng"},{"a","1","bbb","e","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}}, 5}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((String[][]) args[0]);
    }

    String[][] relations;
    List<Integer> candidateFlags;
    int count;
    public int solution(String[][] relation) {

        int columns = relation[0].length;
        this.candidateFlags = new ArrayList<>();
        this.relations = relation;
        this.count = 0;

        // 그룹사이즈 0 ( 한 개 ) 부터 순서대로 탐색한다.
        for(int candSize = 0 ; candSize < columns ; candSize++) {
            for(int x = 0 ; x < columns ; x++) {
                boolean[] selects = new boolean[columns];
                selects[x] = true;
                if(!validMinimality(selects)) continue;
                validCandidateKey(selects, x,candSize);
            }
        }

        return count;
    }

    private void validCandidateKey(boolean[] selects, int x, int needs) {
        if(needs == 0) { // 그룹사이즈가 더 클 필요 없다면, 후보키로 적합한지 확인한다.

            // 후보키 조건으로, 설정된 열에 중복된 데이터가 있으면 안되므로 이를 확인하기위한 리스트다.
            List<String> overlapped = new ArrayList<>();

            for(String[] relation : relations) {
                StringBuilder compare = new StringBuilder();
                for(int key = 0 ; key < relation.length ; key++) if(selects[key]) compare.append(relation[key]).append("-"); // 각 열의 데이터를 -로 이어준 뒤, 중복되는지를 검사한다
                if(overlapped.contains(compare.toString())) break;
                overlapped.add(compare.toString());
            }

            if(overlapped.size() == relations.length) { // 만약 중복된 데이터가 없고, 최소성을 만족한다면 후보키로 추가한다.
                int flag = 0;
                for(int k = 0 ; k < relations[0].length ; k++) flag += (int) (selects[k] ? Math.pow(2, k) : 0);
                candidateFlags.add(flag);
                count++;
            }
        } else { // 그룹사이즈가 더 커야한다면, 추가로 진행한다.
            for(int newKey = x+1 ; newKey < relations[0].length ; newKey++) {
                boolean[] selectsClone = selects.clone();
                selectsClone[newKey] = true;
                if(!validMinimality(selectsClone)) continue;
                validCandidateKey(selectsClone, newKey, needs-1);
            }
        }
    }

    private boolean validMinimality(boolean[] selects) {
        // 최소성을 검사하기 위해 검사대상인 selects와 이미 후보키로 저장된 키들을 flag로 만든 후, AND 연산을 통해 최소성을 만족하는지 확인한다.
        int compare = 0;
        for(int k1 = 0 ; k1 < relations[0].length ; k1++) compare += (int) (selects[k1] ? Math.pow(2, k1) : 0);

        for(int flag : candidateFlags) if((compare & flag) == flag) return false;
        return true;
    }
}

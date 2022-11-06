package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

/**
 * Programmers Lv2
 * 전력망을 둘로 나누기
 *
 * 2022.11.06 PM 15:51 ~ 17:31 1차 시도 실패 - 실패 원인 트리구조 형성에 실패 || Union-find 복습 필요
 * @since 2차 시도 18:20
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/86971
 */
public class Prob68971 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {2, new int[][]{{2, 1}}, 0},
                {8, new int[][]{{1, 2}, {1, 3}, {1,4}, {4, 5}, {5, 6}, {6, 7}, {6, 8}}, 0},
                {5, new int[][]{{1, 3}, {1, 2}, {1, 4}, {1, 5}}, 3},
                {6, new int[][]{{3, 2}, {2, 1}, {1, 4}, {1, 5}, {1, 6}}, 2},
                {9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}, 3},
                {4, new int[][]{{1, 2}, {2, 3}, {3,4}}, 0},
                {7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3,4}, {4, 5}, {6, 7}}, 1},
                {6, new int[][]{{1, 4}, {6, 3}, {2, 5}, {5, 1}, {5, 3}}, 2}
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((int) args[0], (int[][]) args[1]);
    }

    public int solution(int n, int[][] wires) {

        // 1. 초기화
        int[] unionFind = new int[n+1];
        for(int i = 0 ; i <= n ; i++) unionFind[i] = i;


        // 2. Union-Find 알고리즘을 통한 Tree 구성
        for(int[] edge : wires) {
            int childParent = unionFind[edge[1]];
            if(childParent == edge[1]) unionFind[edge[1]] = edge[0]; // 설정하고자 하는 자식의 부모가 자기자신인 경우, 개별 노드 이므로, 그대로 붙인다.
            else unionFind[reverse(unionFind, edge[1])] = edge[0]; // 자기자신이 아닐 경우, 기존의 부모가 자신을 가리키게 한 후, 새로운 부모를 가리킨다.
        }


        // 3. 노드별 자식+본인의 개수를 구한다.
        int[] childCount = new int[n+1];

        // 3-1. root 노드를 찾는다.
        int root = -1;
        for(int node = 1 ; node <= n ; node++) if(unionFind[node] == node) { root = node; break; }

        // 3-2. root 에서 완전탐색을 시작한다.
        bs(childCount, unionFind, root);


        // 4. [문제 풀기] 가장 비슷한 개수로 나누는 방법 : 가장 절반에 가까운 수를 갖는 노드를 쳐낸다.
        float half = n / 2.0f;
        float gap = Float.MAX_VALUE;
        int found = -1;
        for(int node = 1 ; node <= n ; node++) {
            float realGap = Math.abs(childCount[node] - half);
            if(gap > realGap) { gap = realGap; found = node; }
        }

        // 전체에서 자르는 노드의 갯수를 뺀 것 (n-childCount[found])와 잘린 노드(childCount[found])의 차이
        int answer = Math.abs((n-childCount[found]) - childCount[found]);
//        System.out.println(Arrays.toString(unionFind));
//        System.out.println(Arrays.toString(childCount));
//        System.out.println(found + " and " + answer);
        
        return answer;
    }

    private int reverse(int[] array, int befChild) {
        if(array[befChild] != befChild) { // 대상 자식의 부모가 자기자신이 아닐경우 관계를 뒤집는다.
            array[reverse(array, array[befChild])] = befChild;
        }
        return befChild;
    }

    private int bs(int[] count, int[] tree, int node) { // 자신과 자신의 자식들의 수를 구한다.
        int cnt = 1;
        for(int nodeKey = 1 ; nodeKey < tree.length ; nodeKey++) {
            if(nodeKey != node && tree[nodeKey] == node) cnt += bs(count, tree, nodeKey);
        }
        count[node] = cnt;
        return cnt;
    }




/*
    public int solution(int n, int[][] wires) {

        HashMap<Integer, List<Integer>> treeMap = new HashMap<>();
        boolean[] inTree = new boolean[n];

        // 트리 구조 정렬
        for(int[] wire : wires) {
            if(!inTree[wire[1]-1]) { // 1이 트리에 속하지 않은경우 1은 0의 자식
                List<Integer> childs = treeMap.getOrDefault(wire[0], new ArrayList<>());
                childs.add(wire[1]);
                treeMap.put(wire[0], childs);
                inTree[wire[0]-1] = true;
                inTree[wire[1]-1] = true;
            } else if(inTree[wire[1]-1] && !inTree[wire[0]-1]) { //0은 속하지 않고 1은 속한경우, 0은 1의 자식
                List<Integer> childs = treeMap.getOrDefault(wire[1], new ArrayList<>());
                childs.add(wire[0]);
                treeMap.put(wire[1], childs);
                inTree[wire[0]-1] = true;
            }
        }

        for(int k : treeMap.keySet()) {
            System.out.printf("%d -> %s\n", k, Arrays.toString(treeMap.get(k).toArray()));
        }

        int[] childs = new int[n];
        for(int k : treeMap.keySet()) {
            getChilds(treeMap, childs, k);
        }

        float maxDiv2 = n / 2.0f;
        int midKey = -1;
        float midGap = Float.MAX_VALUE;
        System.out.println(Arrays.toString(childs));
        for(int nodeKey = 0 ; nodeKey < n ; nodeKey++) {
            if(childs[nodeKey] == 0) continue;
            if(midGap >= Math.abs(maxDiv2 - childs[nodeKey])) {
                midKey = nodeKey;
                midGap = Math.abs(maxDiv2 - childs[nodeKey]);
            }
        }
        if(midKey == -1) return 0;
        if(childs[midKey] == n) return n-2;

        int cutted = childs[midKey];
        int answer = Math.abs(Math.abs(n-cutted) - cutted);

        System.out.printf("center : %d \t Gap : %f \t Cut Result : %d => %d\n", midKey+1, midGap, cutted, answer);
//        System.out.println(Arrays.toString(childs));

        return answer;
    }

    private int getChilds(HashMap<Integer, List<Integer>> map, int[] childs, int key) {
        if(!map.containsKey(key)) {
            childs[key-1] = 1;
            return 1;
        }
        int counts = 1;
        List<Integer> childNodes = map.get(key);
        for(int key2 : childNodes){
            if(childs[key2-1] == 0)
                counts+=getChilds(map, childs, key2);
            else counts += childs[key2-1];
        }
        childs[key-1] = counts;
        return counts;
    }
*/
}

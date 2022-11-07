package ac.jnu.goop.solutions.level2;

import ac.jnu.goop.SelfTestable;

import java.util.*;

public class Prob72412 implements SelfTestable {
    @Override
    public Object[][] testcases() {
        return new Object[][]{
                {new String[]{"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
                        new String[]{"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"},
                        new int[]{1,1,1,1,2,4}
                }
        };
    }

    @Override
    public Object solution(Object... args) {
        return solution((String[]) args[0], (String[]) args[1]);
    }

    HashMap<String, List<Integer>> db;
    public int[] solution(String[] info, String[] query) {

        // 1. 데이터베이스 추가
        db = new HashMap<>();
        for(String p : info) {
            char[] arr = p.toCharArray();
            char[] data = new char[4];
            int dataKey = 0;
            boolean addMode = true;
            StringBuilder score = new StringBuilder();
            for(int k = 0 ; k < arr.length ; k++) {
                if(addMode) {
                    if(dataKey < 4) {
                        data[dataKey++] = arr[k];
                        addMode = false;
                    } else {
                        score.append(arr[k]);
                    }
                } else if (arr[k] == ' ') {
                    addMode = true;
                }
            }
            int scoreInt = Integer.parseInt(score.toString());

            addQuery(data, scoreInt, 0);
        }

        // 2. Sorting
        int[] answer = new int[query.length];
        HashMap<String, Integer[]> sortDB = new HashMap<>();
        for(String key : db.keySet()) {
            Integer[] target = db.get(key).toArray(new Integer[0]);
            Arrays.sort(target, Comparator.reverseOrder());
            sortDB.put(key, target);
        }

        // 3. Querying
        for(int qNum = 0 ; qNum < query.length ; qNum++) {
            char[] queryText = query[qNum].toCharArray();
            int ignoreSpace = 0;
            StringBuilder index = new StringBuilder();
            StringBuilder score = new StringBuilder();
            for(int k = 0 ; k < queryText.length ; k++) {
                if(ignoreSpace == 0 && index.length() < 4) {
                    index.append(queryText[k]);
                    ignoreSpace = 2;
                } else if (queryText[k] == ' ') {
                    ignoreSpace--;
                } else if (index.length() == 4 && ignoreSpace == 1) {
                    score.append(queryText[k]);
                }
            }

            String indexStr = index.toString();
            int cutScore = Integer.parseInt(score.toString());
            Integer[] scores = sortDB.get(indexStr);
            if(scores == null || scores.length <= 0) { answer[qNum] = 0; continue; }
            answer[qNum] = binarySearch(scores, cutScore);
        }

        return answer;
    }

    private void addQuery(char[] data, int score, int key) {
        if(key == data.length) {
            StringBuilder builder = new StringBuilder();
            for(char c : data) builder.append(c);
            String index = builder.toString();
            if(!db.containsKey(index)) db.put(index, new ArrayList<>());
            db.get(index).add(score);
        } else {
            char[] clone = data.clone();
            clone[key] = '-';
            addQuery(data, score, key+1);
            addQuery(clone, score, key+1);
        }
    }

    private int binarySearch(Integer[] array, int score) {
        int minKey = 0, maxKey = array.length - 1, half;
        while(minKey <= maxKey) {
            half = ( minKey + maxKey ) / 2;
            if(array[half] >= score) minKey = half + 1;
            else maxKey = half - 1;
        }
        return maxKey+1;
    }

/*    HashMap<String, List<Integer>> db;
    public int[] solution(String[] info, String[] query) {
        db = new HashMap<>();
        for(String p : info) {
            String[] split = p.split(" ");
            addQuery(split, Integer.parseInt(split[4]));
        }

        int[] answer = new int[query.length];

        for(int qNum = 0 ; qNum < query.length ; qNum++) {

            String q = query[qNum];
            String[] split = q.split(" ");
            StringBuilder builder = new StringBuilder();
            builder.append(split[0]).append(split[2]).append(split[4]).append(split[6]);
            String queryStr = builder.toString();
            int score = Integer.parseInt(split[7]);

            Integer[] results = new ArrayList<>(db.getOrDefault(queryStr, new ArrayList<>())).toArray(new Integer[0]);
            if(results.length == 0){ answer[qNum] = 0; continue; }
            Arrays.sort(results);
            answer[qNum] = binarySearch(results, score);
        }

        return answer;
    }

    private void addQuery(String[] query, int score) {
        addQuery(query, score, 0);
    }

    private void addQuery(String[] query, int score, int startKey) {
        if(startKey == query.length-1) {
            StringBuilder builder = new StringBuilder();
            for(int append = 0 ; append < query.length-1 ; append++) builder.append(query[append]);
            List<Integer> list = db.getOrDefault(builder.toString(), new ArrayList<>());
            list.add(score);
            db.put(builder.toString(), list);
        } else {
            String[] clone = query.clone();
            clone[startKey] = "-";
            addQuery(query, score, startKey+1);
            addQuery(clone, score, startKey+1);
        }
    }

    private int binarySearch(Integer[] array, int cut) {
        int minKey = 0, maxKey = array.length-1;
        System.out.println(Arrays.toString(array));
        while(Math.abs(minKey - maxKey) > 1) {
            int half = (minKey + maxKey) / 2;
            int halfVal = array[half];
            if(halfVal >= cut) maxKey = half;
            else minKey = half;
        }
        return array.length - maxKey;
    }*/
}

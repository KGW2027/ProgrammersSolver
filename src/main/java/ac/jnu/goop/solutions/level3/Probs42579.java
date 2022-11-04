package ac.jnu.goop.solutions.level3;

import ac.jnu.goop.Testable;

import java.util.*;

public class Probs42579 implements Testable {
    @Override
    public Object solution(Object... args) {
        return solution((String[]) args[0], (int[]) args[1]);
    }


    class SongInfo implements Comparable<SongInfo> {
        int songNum;
        int played;

        SongInfo(int a, int b) {
            songNum = a; played = b;
        }

        @Override
        public int compareTo(SongInfo o) {
            if(this.played > o.played) return 1;
            else if(this.played < o.played) return -1;
            return 0;
        }
    }

    HashMap<String, Integer> genrePlayed;

    public int[] solution(String[] genres, int[] plays) {
        genrePlayed = new HashMap<>();
        HashMap<String, List<SongInfo>> mostPlayed = new HashMap<>();

        for(int song = 0 ; song < genres.length ; song++) {
            genrePlayed.put(genres[song], genrePlayed.getOrDefault(genres[song], 0) + plays[song]);

            List<SongInfo> playedMap = mostPlayed.getOrDefault(genres[song], new ArrayList<>());
            playedMap.add(new SongInfo(song, plays[song]));
            mostPlayed.put(genres[song], playedMap);
        }

        List<Integer> mostPlayGenre = new ArrayList<>(genrePlayed.values());
        mostPlayGenre.sort(Comparator.reverseOrder());
        Integer[] mostPlayGenreArray = mostPlayGenre.toArray(new Integer[0]);

        List<Integer> songs = new ArrayList<>();

        for(int i = 0 ; i < mostPlayGenreArray.length ; i++) {
            String genreName = findGenreNameByPlayed(mostPlayGenreArray[i]);
            List<SongInfo> genrePlayed = mostPlayed.get(genreName);
            genrePlayed.sort(Collections.reverseOrder());
            for(int add = 0 ; add < Math.min(2, genrePlayed.size()) ; add++)
                songs.add(genrePlayed.get(add).songNum);
        }

        Integer[] songsArray = songs.toArray(new Integer[0]);
        int[] answer = new int[songsArray.length];
        for(int i = 0 ; i < songsArray.length ; i++) answer[i] = songsArray[i];

        return answer;
    }

    private String findGenreNameByPlayed(int played) {
        for (String s : genrePlayed.keySet())
            if (genrePlayed.get(s) == played) return s;
        return null;
    }
}

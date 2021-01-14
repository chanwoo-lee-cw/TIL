import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Rank> ranking = new ArrayList<>();
        ranking.add(new Rank("Harry", 90));
        ranking.add(new Rank("Betty", 90));
        ranking.add(new Rank("Ben", 95));
        ranking.add(new Rank("Alex", 95));
        ranking.add(new Rank("James", 93));

        PriorityQueue<Rank> que = new PriorityQueue<>(new Comparator<Rank>() {
            @Override
            public int compare(Rank o1, Rank o2) {
                // 점수를 오름차 순으로 정렬
                if (o1.score > o2.score) {
                    return 1;
                } else if (o1.score < o2.score) {
                    return -1;
                } else {
                    // 이름을 오름차 순으로 정렬
                    return o1.name.compareTo(o2.name);
                }
            }
        });
        for (Rank curr : ranking) {
            que.add(curr);
        }
        while (!que.isEmpty()) {
            Rank curr = que.poll();
            System.out.println(String.format("%s : %d", curr.name, curr.score));
        }

        System.out.println("---------------------------------------");

        Collections.sort(ranking, new Comparator<Rank>() {
            @Override
            public int compare(Rank o1, Rank o2) {
                // 뒤에 것을 뒤에 두고 싶다면 양수 리턴
                if (o1.score < o2.score) {
                    return 1;
                } else if (o1.score > o2.score) {
                    return -1;
                } else {
                    // 이름을 내침 차 순으로 정렬
                    return -o1.name.compareTo(o2.name);
                }
            }
        });
        for (Rank curr : ranking) {
            System.out.println(String.format("%s : %d", curr.name, curr.score));
        }
    }
}

class Rank {
    String name;
    int score;

    Rank(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
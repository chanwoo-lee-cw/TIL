import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        Solution solution = new Solution();

        for (int i = 0; i < n; i++) {
            String fileName = bufferedReader.readLine();
            solution.inputFileExt(fileName);
        }

        solution.printExtList();
    }
}

class Solution {
    Map<String, Integer> map = new HashMap<>();
    Set<String> set = new HashSet<>();


    public void inputFileExt(String fileName) {
        String ext = fileName.split("\\.")[1];

        if (!map.containsKey(ext)) {
            map.put(ext, 0);
        }
        map.put(ext, map.get(ext) + 1);
        set.add(ext);
    }

    public void printExtList() {
        PriorityQueue<String> queue = new PriorityQueue<>(set);
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            String currExt = queue.poll();
            stringBuilder.append(String.format("%s %d", currExt, map.get(currExt))).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}
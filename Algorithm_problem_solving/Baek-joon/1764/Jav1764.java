import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;
        HashSet<String> no_hear_person = new HashSet<>();
        HashSet<String> no_see_person = new HashSet<>();

        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                no_hear_person.add(bf.readLine());
            }
            for (int i = 0; i < m; i++) {
                no_see_person.add(bf.readLine());
            }

            no_hear_person.retainAll(no_see_person);

            System.out.println(no_hear_person.size());

            List<String> names = new ArrayList<>(no_hear_person);
            Collections.sort(names);

            for (String name : names) {
                System.out.println(name);
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
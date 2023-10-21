import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, List<Person>> ageByPerson = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(bf.readLine());

            StringTokenizer st = null;
            int age;
            String name;
            Person newPerson;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(bf.readLine());
                age = Integer.parseInt(st.nextToken());
                name = st.nextToken();
                newPerson = new Person(age, name);
                if (ageByPerson.containsKey(age)) {
                    ageByPerson.get(age).add(newPerson);
                } else {
                    List<Person> ageList = new ArrayList<>();
                    ageList.add(newPerson);
                    ageByPerson.put(age, ageList);
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= 200; i++) {
                if (ageByPerson.containsKey(i)) {
                    for (Person person : ageByPerson.get(i)) {
                        sb.append(String.format("%d %s", i, person.getName()));
                        sb.append("\n");
                    }
                }
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
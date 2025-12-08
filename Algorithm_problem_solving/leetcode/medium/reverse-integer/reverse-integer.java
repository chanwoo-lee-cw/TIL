import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

class Solution {
    public int reverse(int x) {
        ArrayList<Integer> maxInt = new ArrayList<>();
        Integer.toString(Integer.MAX_VALUE).chars().map(i -> i - '0').forEach(maxInt::add);

        ArrayList<Integer> minInt = new ArrayList<>();
        Integer.toString(Integer.MIN_VALUE).chars().filter(it -> it != '-').map(i -> i - '0').forEach(minInt::add);

        ArrayList<Integer> intToArray = new ArrayList<>();
        Integer.toString(x).chars().filter(it -> it != '-').map(i -> i - '0').forEach(intToArray::add);

        Collections.reverse(intToArray);

        if (x >= 0) {
            if (intToArray.size() == maxInt.size()) {
                for (int i = 0; i < maxInt.size(); i++) {
                    if (intToArray.get(i) > maxInt.get(i)) {
                        return 0;
                    } else if (Objects.equals(intToArray.get(i), maxInt.get(i))) {
                        // do nothing
                    } else {
                        break;
                    }
                }
            }
            return Integer.parseInt(intToArray.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        } else {
            if (intToArray.size() == minInt.size()) {
                for (int i = 0; i < minInt.size(); i++) {
                    if (intToArray.get(i) > minInt.get(i)) {
                        return 0;
                    } else if (Objects.equals(intToArray.get(i), maxInt.get(i))) {
                        // do nothing
                    } else {
                        break;
                    }
                }
            }
            return -Integer.parseInt(intToArray.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        }
    }
}

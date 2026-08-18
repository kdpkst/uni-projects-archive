package lab3.MatchSwap;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class MatchSwap {

    /**
     * Modify a list of strings such that two strings with same
     * first letter are swapped.
     * For example, matchSwap(["ap", "bp", "cp", "aq", "cq", "bq"]) →
     * ["aq", "bq", "cq", "ap", "cp", "bp"].
     * @param list is a list of strings.
     * The strings are non-empty.
     * @return the modified list.
     */

    public static List<String> matchSwap(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i) {
            String firstLetter = String.valueOf(list.get(i).charAt(0));
            if (map.containsKey(firstLetter)) {
                int index = map.get(firstLetter);
                String temp = list.get(index);
                list.set(index, list.get(i));
                list.set(i, temp);
                map.remove(firstLetter);
            }
            else {
                map.put(firstLetter, i);
            }
        }
		return list;
    }

}
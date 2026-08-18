package lab3.SameFirstLetter;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class SameFirstLetter {

    /**
     * Create a map with first letter as key and words with that same
     * first letter separated by comma.
     * For example, numWords(["alice", "bob", "apple", "banana"]) →
     * {"a": "alice,apple", "b": "bob,banana"}.
     * @param list is a list of strings.
     * The strings are non-empty.
     * @return a map with first letter and comma-separated-words pair.
     */
    public static Map<String, String> sameFirstLetter(List<String> list) {
        Map<String, String> map = new HashMap<>();
        String temp = "";
        for (int i = 0; i < list.size(); ++i){
            String firstLetter = String.valueOf(list.get(i).charAt(0));
            if (map.containsKey(firstLetter)){
                temp = map.get(firstLetter);
                temp += "," + list.get(i);
                map.put(firstLetter, temp);
            }
            else {
                map.put(firstLetter, list.get(i));
            }
        }
        return map;
    }

}
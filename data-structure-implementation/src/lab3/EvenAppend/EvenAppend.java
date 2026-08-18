package lab3.EvenAppend;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EvenAppend {

    /**
     * Append words that appear the 2nd, 4th, 6th, etc. time in a list.
     * For example, evenAppend(["a", "b", "b", "a", "a", "b", "b"]) → "bab".
     * @param list is a list of words.
     * @return a concatenation of even appearing words.
     */
    public static String evenAppend(List<String> list) {

        String resultStr = "";
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i){
            if(map.containsKey(list.get(i))){
                map.put(list.get(i), map.get(list.get(i)) + 1);
                if (map.get(list.get(i)) % 2 == 0) resultStr += list.get(i);
            }
            else map.put(list.get(i), 1);
        }
        return resultStr;

    }

}
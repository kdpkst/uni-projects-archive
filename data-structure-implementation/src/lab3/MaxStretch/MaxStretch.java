package lab3.MaxStretch;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class MaxStretch {

    /**
     * Find the largest stretch in a list.
     * For example, maxStretch([8, 5, 1, 2, 3, 4, 5, 10]) = 6.
     * @param list is a list of integers.
     * @return the largest stretch in list.
     */

    // add more testing cases!!!!
    public static int maxStretch(List<Integer> list) {
        if (list.size() == 1) return 1;

        int max = 0;
        int temp = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); ++i){
            if (map.containsKey(list.get(i))){
                temp = i - map.get(list.get(i)) + 1;
                if (temp > max) max = temp;
            }
            else map.put(list.get(i), i);
        }
        return max;
    }


    public static int maxStretch_2(List<Integer> list) {
        int max = 0;

        return max;
    }

}
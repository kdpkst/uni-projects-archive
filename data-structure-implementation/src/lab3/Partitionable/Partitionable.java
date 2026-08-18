package lab3.Partitionable;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Partitionable {

    /**
     * Decide whether a list is partitionable.
     * For example, isPartitionable([1, 1, 1, 2, 1]) -> true,
     * and isPartitionable([2, 1, 1, 2, 1]) -> false.
     * @param list is a non-empty list of integers.
     * @return true iff list is partitionable.
     */
    public static boolean isPartitionable(List<Integer> list) {

        for (int i = 0; i < list.size(); ++i){
            int leftSum = 0;
            int rightSum = 0;
            for (int j = 0; j < i ; ++j){
                leftSum += list.get(j);
            }
            for (int k = i; k < list.size(); ++k){
                rightSum += list.get(k);
            }
            if (rightSum == leftSum) {
                return true;
            }

        }
        return false;

    }
}
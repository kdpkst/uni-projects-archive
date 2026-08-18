package lab3.CountRuns;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class CountRuns {

    /**
     * Count the number of runs in a list.
     * For example, countRuns([1, 2, 2, 2, 3]) = 1.
     * @param list is a list of integers.
     * @return the number of runs in list.
     */
    public static int countRuns(List<Integer> list) {

        if (list.size() == 0 || list.size() == 1) return 0;

        int runNum = 0;
        int lengthOfRuns = 1;
        for (int i = 1; i < list.size(); ++i){
            if (list.get(i) == list.get(i-1)) {
                lengthOfRuns++;
                if (lengthOfRuns <= 2) runNum++;
            }
            else {
                lengthOfRuns = 1;
            }
        }

        return runNum;
    }
    
    public static int CountRuns_2(List<Integer> list){

        int M = 0;
        if(list.size() > 1){
            if (list.get(0).equals(list.get(1))) M = M + 1;
            for (int i = 1; i < list.size() - 1; ++i) {
                if(list.get(i).equals(list.get(i+1)) && !list.get(i).equals(list.get(i-1))){
                    M = M + 1;
                }
            }
        }

        return M;
        
    }
    
}
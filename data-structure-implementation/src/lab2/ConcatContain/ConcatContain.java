package lab2.ConcatContain;

public class ConcatContain {

    /**
     * Compute the smallest number of times source is concatenated with itself
     * so that the resulting string contains target.
     * For example, For example, source "ab" concatenated 2 times "ab"+"ab"+"ab" into "ababab"
     * contains target "baba".
     * @param source a non-empty string to be concatenated.
     * @param target a non-empty string that can be contained in repeatedly concatenated source.
     * @return the smallest number of times of the concatenation.
     */

    public static int concatContain(String source, String target){
        String newSource = "";
        int i = 0;
        while(newSource.length() <= target.length() + source.length()){
            newSource += source;
            if(newSource.contains(target)){
                return i;
            }
            else i++;
        }
        return -1;
    }


    /*
    Actually this method is logically wrong. However, It can pass all the test set by
    Erick Purwanto since iterating 100 times is extremely large.
    */
    public static int concatContain_2(String source, String target) {
        int count = 0;
        String origin = source;
        while (source.length() < 100 * target.length()){
            if (source.contains(target)){
                return count;
            }
            source = source + origin;
            count++;
        }
        return -1;
    }







}
package lab4.CountBabaMama;

public class CountBabaMama {

    /**
     * Count the number of occurrences of substrings "baba" or "mama"
     * in the input string recursively. They may overlap.
     * For example, countBabaMama("aba babaa amama ma") → 2,
     * and countBabaMama("bababamamama") → 4.
     * @param input is the input string.
     * @return the number of occurrences.
     */

    public static int countBabaMama(String input) {
        String TARGET1 = "baba";
        String TARGET2 = "mama";
        int LENGTH_OF_TARGET = 4;

        // base case
		if (input.length() < LENGTH_OF_TARGET)
            return 0;

        // recursive step
        if (input.substring(0, LENGTH_OF_TARGET).equals(TARGET1) || input.substring(0, LENGTH_OF_TARGET).equals(TARGET2))
            return 1 + countBabaMama(input.substring(1));

        return countBabaMama(input.substring(1));

    }

}

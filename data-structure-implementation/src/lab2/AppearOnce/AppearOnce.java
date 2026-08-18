package lab2.AppearOnce;

public class AppearOnce {

    /**
	 * Returns the first character that appears exactly once in the string.
	 * For example, appearOnce("abca") -> 'b'.
	 * @param input a non-empty string.
	 * @return the first character that appears exactly once.
	 * @return the new line character if there is no character that appears exactly once.
	 */


	// core idea: count the number of appearance times for each char in the input string.
	// use data structure (array with length of 26)
	// find the first letter
	public static char appearOnce(String input) {
		int[] count = new int[26];  // 26 letters
		for (int i = 0; i < input.length(); i++){
			count[input.charAt(i) - 'a']++;
		}

		for (int j = 0; j < input.length(); j++){
			if (count[input.charAt(j) - 'a'] == 1)
				return input.charAt(j);
		}

		return '\n';
	}


	// same idea as above one (counting the number of appearance times). But O(n2)
	public static char appearOnce_2(String input) {
		int[] count = new int[input.length()];

		for (int i = 0; i < input.length(); i++){
			for (int j = 0; j < input.length(); j++){
				if (input.charAt(i) == input.charAt(j)){
					count[i]++;
				}
			}
		}

		for (int i = 0; i < input.length(); i++){
			if(count[i] == 1) return input.charAt(i);
		}

		return '\n';
	}


	 public static char appearOnce_3(String input) {
		 for (int i = 0; i < input.length(); i++) {
			 char c = input.charAt(i);
			 // indexOf() only return the first index of a specific char appeared in the string.
			 // input.indexOf ensures that no repeated char before the current position.
			 if (input.indexOf(c) == i && input.indexOf(c, i + 1) == -1) {
				 return c;
			 }
		 }
		 return '\n';
	}





}
package lab8;

public class RegexExercise {

    // LAB EXERCISE #8.1
    // Returns a regular expression string for a pattern xxx-y-zzzz
    // where x is a digit, y is at least 2 digits, and z is a non-zero digit
    public static String getPattern1() {
        String pattern1 = "[0-9]{3}-[0-9]{2}-[1-9]{4}";
        return pattern1;

    }

    // CODING ASSIGNMENT #8.1
    // Returns a regular expression string for a pattern xxx-y-z
    // where x is any letter, digit, or the underscore character
    // y is an uppercase letter or $ (dollar sign), and
    // z is an even number
    public static String getPattern2() {
        String pattern2 = "[a-z|A-Z|0-9|_]{3}-[A-Z|$]-[0-9]*[02468]$";
        return pattern2;
    }

    public static void main(String[] args) {
        String s1 = "111-22-3333";
        System.out.println(s1.matches(getPattern1()));  // true

        s1 = "123-4-5555";
        System.out.println(s1.matches(getPattern1()));  // false

        s1 = "111-22-3450";
        System.out.println(s1.matches(getPattern1()));  // false


        String s2 = "a_1-B-538";
        System.out.println(s2.matches(getPattern2()));  // true

        s2 = "PQR-$-10";
        System.out.println(s2.matches(getPattern2()));  // true

        s2 = "ab-k-249";
        System.out.println(s2.matches(getPattern2()));  // false


    }
}

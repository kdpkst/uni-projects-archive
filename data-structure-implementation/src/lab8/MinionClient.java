package lab8;


public class MinionClient {

    // LAB EXERCISE #8.2 : with switch
    public static String getMinionQuote1(Minion minion) {

        switch (minion){
            case KEVIN:
                return Minion.KEVIN.getQuote();
            case BOB:
                return Minion.BOB.getQuote();
            case STUART:
                return Minion.STUART.getQuote();
        }
		
		return "";
    }

    // CODING ASSIGNMENT #8.2 : without switch
    public static String getMinionQuote2(Minion minion) {
        return minion.getQuote();
    }

    public static void main(String[] args) {

        Minion minion = Minion.KEVIN;
        System.out.println(getMinionQuote1(minion));
        minion = Minion.BOB;
        System.out.println(getMinionQuote1(minion));
        minion = Minion.STUART;
        System.out.println(getMinionQuote1(minion));

        minion = Minion.KEVIN;
        System.out.println(getMinionQuote2(minion));
        minion = Minion.BOB;
        System.out.println(getMinionQuote2(minion));
        minion = Minion.STUART;
        System.out.println(getMinionQuote2(minion));





    }

}

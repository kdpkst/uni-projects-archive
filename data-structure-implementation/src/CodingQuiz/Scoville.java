package CodingQuiz;


// Scoville Scale gives rank and hotness range (low, high)
// of various peppers and chilis.
public enum Scoville {

    NOTHOT(0, 100),
    MILD(100, 2500), MEDIUM(2500, 30000),
    HOT(30000, 100000), EXTRAHOT(100000, 300000),
    EXTREMEHOT(300000, 3000000);

    final int low, high;

    Scoville(int low, int high) {
        this.low = low;
        this.high = high;
    }

    // FINAL PROJECT PART B.1
    // returns the rank of a particular Scoville Scale value starting from 1.
    public int rankScoville() {
        // Your code here
        return this.ordinal() + 1;
    }

    // returns the average of the range of a particular Scoville Scale value.
    public int averageScoville() {
        // Your code here
        return (this.low + this.high) / 2;
    }


    public static void main(String[] args) {
        System.out.println(Scoville.NOTHOT.rankScoville());
        System.out.println(Scoville.NOTHOT.averageScoville());

        System.out.println(Scoville.MILD.rankScoville());
        System.out.println(Scoville.MILD.averageScoville());

    }


}
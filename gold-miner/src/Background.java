import java.awt.*;


public class Background {
    static int level = 1;
    static int n = 0;
    static int goal = level * 15 + 3 * n;
    static int totalPoints = 0;
    static int agentiaNum = 2;
    static boolean agentiaFlag = false;  // mark whether is using agentia
    static long startTime;
    static long endTime;
    static int agentiaPrice = (int) (Math.random() * 10);
    static boolean purchase = false;
    Image bg = Toolkit.getDefaultToolkit().getImage("images/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("images/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("images/peo.png");
    Image agentia = Toolkit.getDefaultToolkit().getImage("images/agentia.png");


    void paintSelf(Graphics g){
        g.drawImage(bg, 0, 200, null);
        g.drawImage(bg1, 0, 0, null);
        switch (GameWin.state) {
            case 0:
                printWords(g, Color.GREEN, 70, "Right click to start!", 78, 400);
                break;
            case 1:
                g.drawImage(peo, 310, 50, null);
                g.drawImage(agentia, 500, 40, null);
                printWords(g, Color.BLACK, 30, "*" + agentiaNum, 570, 70);
                printWords(g, Color.BLACK, 20, "Level " + level, 30, 60);
                printWords(g, Color.BLACK, 30, "Points: " + totalPoints, 30, 100);
                printWords(g, Color.BLACK, 30, "Goal: " + goal, 30, 140);
                endTime = System.currentTimeMillis();
                long countDown = 20 - (endTime - startTime) / 1000;
                printWords(g, Color.BLACK, 30, "Time: " + (countDown>0?countDown:0), 500, 160);
                break;
            case 2:
                printWords(g, Color.BLACK, 40, "Current price: " + agentiaPrice, 250, 280);
                g.drawImage(agentia, 300, 300, null);
                printWords(g, Color.BLACK, 40, "left click to Purchase?", 190, 420);
                printWords(g, Color.BLACK, 40, "or right click to go to next level?", 100, 470);
                if (purchase) {
                    totalPoints = totalPoints - agentiaPrice;
                    agentiaNum++;
                    purchase = false;
                    GameWin.state = 1;
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3:
                printWords(g, Color.RED, 70, "Fail!", 285, 250);
                printWords(g, Color.RED, 70, "Points: " + totalPoints, 220, 350);
                printWords(g, Color.RED, 50, "Left click to restart", 140, 450);
                break;
        }

    }

    // print the strings to the game window
    void printWords(Graphics g, Color color, int size, String str, int x, int y){
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, size));
        g.drawString(str, x, y);
    }

    static boolean timeIsOver(){
        long time = (endTime - startTime) / 1000;
        if (time > 20) {return true;}
        return false;
    }

    static void reset(){
        level = 1;
        goal = level * 15;
        totalPoints = 0;
        agentiaNum = 2;
        agentiaFlag = false;
    }
}

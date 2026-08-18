import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;


public class GameWin extends JFrame{
    static int state;  // 0--not start, 1--running, 2--shop, 3--failure
    static int width = 768;
    static int height = 820;
    static int goldNum = 8;
    static int coalNum = 3;
    List<Object> objectList = new ArrayList<>();  // store gold and coal

    {
        boolean isPlace = true;
        for (int i = 0; i < goldNum; i++) {
            double randomNum = Math.random();
            Gold gold;
            if (randomNum < 0.3) {gold = new GoldMini();}
            else if (randomNum < 0.5) {gold = new GoldLarge();}
            else {gold = new Gold();}

            for (Object obj : objectList) {
                if (gold.getRec().intersects(obj.getRec())) {isPlace = false;}
            }

            if (isPlace) {objectList.add(gold);}
            else {
                isPlace = true;
                i--;
            }
        }

        for (int j = 0; j < coalNum; j++){
            Coal coal = new Coal();

            for (Object obj : objectList) {
                if (coal.getRec().intersects(obj.getRec())) {isPlace = false;}
            }

            if (isPlace) {objectList.add(coal);}
            else {
                isPlace = true;
                j--;
            }
        }
    }

    Background bg = new Background();
    Line line = new Line(this);
    Image offScreenImage;

    void launch(){
        this.setVisible(true);
        this.setSize(width, height);
        this.setLocationRelativeTo(null); // center the window
        this.setTitle("Gold Miner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (state) {
                    case 0:
                        if (e.getButton() == 3) {
                            state = 1;
                            Background.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        if (e.getButton() == 1 && line.state == 0) {line.state = 1;}
                        if (e.getButton() == 3 && line.state == 3) {
                            Background.agentiaFlag = true;
                            if (Background.agentiaNum >= 0){
                                if (Background.agentiaNum >= 1) {Background.agentiaNum--;}
                                else {Background.agentiaFlag = false;}
                            }
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {Background.purchase = true;}
                        if (e.getButton() == 3) {
                            state = 1;
                            Background.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 3:
                        if (e.getButton() == 1) {
                            state = 0;
                            Background.reset();
                        }
                        break;
                }
            }
        });

        while (true){
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // function for entering to next level
    public void nextLevel(){
        if (state == 1 && Background.timeIsOver()){
            if (Background.totalPoints >= Background.goal) {
                state = 2;
                Background.level++;
                Background.n++;
                Background.goal = Background.level * 15 + 4 * Background.n;
            }
            else{state = 3;}
            dispose();
            GameWin gameWin = new GameWin();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics g){
        offScreenImage = this.createImage(width, height);
        Graphics gImage = offScreenImage.getGraphics();
        bg.paintSelf(gImage);
        if (state == 1){
            for (Object obj: objectList){
                obj.paintSelf(gImage);
            }
            line.paintSelf(gImage);
        }

        g.drawImage(offScreenImage, 0, 0, null);
    }


    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}

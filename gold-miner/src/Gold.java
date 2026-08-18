import java.awt.*;


public class Gold extends Object{

    Gold() {
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 350 + 300);
        this.width = 52;
        this.height = 52;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold1.gif");
        this.flag = false;
        this.mass = 30;
        this.point = 4;
        this.type = 1;
    }
}

class GoldMini extends Gold {
    GoldMini(){
        this.x = (int) (Math.random() * 725);
        this.y = (int) (Math.random() * 400 + 300);
        this.width = 36;
        this.height = 36;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold0.gif");
        this.flag = false;
        this.mass = 20;
        this.point = 2;
    }
}

class GoldLarge extends Gold {
    GoldLarge(){
        this.x = (int) (Math.random() * 660);
        this.y = (int) (Math.random() * 300 + 300);
        this.width = 105;
        this.height = 105;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold2.gif");
        this.flag = false;
        this.mass = 70;
        this.point = 6;
    }
}
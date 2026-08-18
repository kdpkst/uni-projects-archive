import java.awt.*;


public class Coal extends Object{
    Coal() {
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 350 + 300);
        this.width = 71;
        this.height = 71;
        this.img = Toolkit.getDefaultToolkit().getImage("images/coal.png");
        this.flag = false;
        this.mass = 50;
        this.point = 1;
        this.type = 2;
    }


}

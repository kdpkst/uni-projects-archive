import java.awt.*;


public class Line {
    // start point
    int x = 380;
    int y = 180;
    // end point
    int endx;
    int endy;
    double length = 100;
    double MIN_length = 100;
    double MAX_length = 650;
    double swing_factor = 0;
    int dir; // dir=1 clockwise; dir=-1 anticlockwise
    int state; // the state of the line: 0--swing, 1--extend, 2--withdraw 3--catch&&return
    GameWin frame;
    Image hook = Toolkit.getDefaultToolkit().getImage("images/hook.png");;

    Line(GameWin frame){this.frame = frame;}

    void collision(){
        for (Object obj:this.frame.objectList) {
            if (endx > obj.x && endx < obj.x + obj.width
                    && endy > obj.y && endy < obj.y + obj.height) {
                state = 3;
                obj.flag = true;
            }
        }
    }

    void paintLine(Graphics g){
        endx = (int)(x + length * Math.cos(swing_factor * Math.PI));
        endy = (int)(y + length * Math.sin(swing_factor * Math.PI));
        g.setColor(Color.red);
        g.drawLine(x, y, endx, endy);
        g.drawLine(x+1, y+1, endx+1, endy+1);
        g.drawLine(x-1, y-1, endx-1, endy-1);
        g.drawImage(hook, endx - 36, endy - 8, null);
    }

    void paintSelf(Graphics g){
        collision();
        switch (state) {
            case 0:  // swing
                if (swing_factor < 0.1) dir = 1;
                if (swing_factor > 0.9)  dir = -1;
                swing_factor += 0.005*dir;
                paintLine(g);
                break;
            case 1:  // extend
                if (length < MAX_length){
                    length += 7;
                    paintLine(g);
                }
                else {state = 2;}
                break;
            case 2:  // withdraw
                if (length > MIN_length){
                    length -= 7;
                    paintLine(g);
                }
                else {state = 0;}
                break;
            case 3:
                int objectMass = 0;
                if (length > MIN_length){
                    length -= 7;
                    paintLine(g);
                    for (Object obj:this.frame.objectList) {
                        if (obj.flag) {
                            objectMass = obj.mass;
                            obj.x = endx - obj.getWidth() / 2;
                            obj.y = endy;
                            if (length <= MIN_length){
                                obj.x = -200;
                                obj.y = -200;
                                obj.flag = false;
                                Background.agentiaFlag = false;
                                // the total points of the game
                                Background.totalPoints += obj.point;
                                state = 0;
                            }
                            if (Background.agentiaFlag) {
                                if (obj.type == 1){
                                    objectMass = 1;
                                }
                                if (obj.type == 2){
                                    obj.x = -200;
                                    obj.y = -200;
                                    obj.flag = false;
                                    Background.agentiaFlag = false;
                                    state = 2;
                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(objectMass);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

}

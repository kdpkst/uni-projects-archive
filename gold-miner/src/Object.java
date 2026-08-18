import java.awt.*;


public class Object {
    int x;
    int y;
    int width;
    int height;
    Image img;
    boolean flag;  // mark whether the object can move
    int mass;
    int point;
    int type;  // gold--type1, coal--type2
    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    int getWidth() {
        return this.width;
    }

    Rectangle getRec(){
        return new Rectangle(x, y, width, height);
    }
}

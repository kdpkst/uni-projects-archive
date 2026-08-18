
package CW3;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private final Image myImage;
    private final int myX, myY;
    private final int myWidth, myHeight;
    private final Person id;
    
    public ImagePanel(Person dispID,int myX,int myY,int myWidth,int myHeight) {
        // Constructor for image panel
        myImage = dispID.getPhoto();
        this.id = dispID;
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
       
        
        // Complete this method
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the wanted poster
        int width = 400;
        int height = 600;
        Rectangle clip = g.getClipBounds(); 
                
        Graphics2D g0 = (Graphics2D) g;     //set background color
        g0.setPaint(new GradientPaint(0,0,new Color(36,181,77),getWidth(),getHeight(),new Color(151,53,157)));
        g0.fillRect(0,0,getWidth(),getHeight());
        
        g.setColor(Color.black);
        Font ft0 = new Font("Serif",Font.BOLD,40);
        g.setFont(ft0);
        String text1 = "WANTED";
        FontMetrics fm1 = g.getFontMetrics(ft0);
        int textWidth1 = fm1.stringWidth(text1);
        int x1 = (clip.width - textWidth1) / 2;
        g.drawString(text1,x1,45);
        g.fillRect(0, 55, getWidth(), 6);
        
        g.setColor(Color.black);
        Font ft1 = new Font("Serif",Font.BOLD,20);
        g.setFont(ft1);
        String crimes = id.getCrimes();   
        FontMetrics fm2 = g.getFontMetrics(ft1);
        int textWidth2 = fm2.stringWidth(crimes);
        int x2 = (clip.width - textWidth2) / 2;
        g.drawString(crimes,x2,83);
        
        g.setColor(Color.WHITE);
        g.fillRect((clip.width - 150)/2,95,150,200);
        g.setColor(Color.black);
        g.drawRect((clip.width - 150)/2,95,150,200);
        g.drawImage(myImage, (clip.width - 140)/2, 100, 140, 190, this);
        
        g.setColor(Color.red);
        Font ft = new Font("serif",Font.BOLD,30);
        g.setFont(ft);
        FontMetrics fm0 = g.getFontMetrics(ft);
        String text0 = "DEAD OR ALIVE";
        int textWidth0 = fm0.stringWidth(text0);
        int x0 = (clip.width - textWidth0) / 2;
        g.drawString(text0,x0,330);
              
        g.setColor(Color.lightGray);
        Font ft2 = new Font("Serif",Font.BOLD,20);
        g.setFont(ft2);
        String firstName = id.getFirstName();
        String familyName = id.getFamilyName();
        String nickName = id.getNickname();
        FontMetrics fm3 = g.getFontMetrics(ft2);
        int textWidth3 = fm3.stringWidth(firstName + " " + familyName + " " + "\"" + nickName + "\"");
        int x3 = (clip.width - textWidth3) / 2;
        g.drawString(firstName + " " + familyName + " " + "\"" + nickName + "\"",x3,378);
         g.setColor(Color.black);
        g.fillRect((clip.width - 270)/2,350,270,5); 
        g.fillRect((clip.width - 270)/2,390,270,5);
        
        g.setColor(Color.black);
        Font ft3 = new Font("Serif",Font.BOLD,20);
        g.setFont(ft3);

        String age = Integer.toString(id.getAgeinYears());
        FontMetrics fm4 = g.getFontMetrics(ft3);
        String reward = Integer.toString(id.getReward());
        int textWidth4 = fm4.stringWidth("Reward $" + reward + ", "+ age + " years old");
        int x4 = (clip.width - textWidth4) / 2;        
        g.drawString("Reward $" + reward + ", "+ age + " years old",x4,440);
        
        g.setColor(Color.black);
        Font ft4 = new Font("Serif",Font.BOLD,20);
        g.setFont(ft4);
        String nationality = id.getNationality();
        FontMetrics fm5 = g.getFontMetrics(ft4);
        int textWidth5 = fm5.stringWidth("Nationality: " + nationality);
        int x5 = (clip.width - textWidth5) / 2;        
        g.drawString("Nationality: " + nationality,x5,480);
        
        g.setColor(Color.black);
        Font ft5 = new Font("Serif",Font.BOLD,20);
        g.setFont(ft5);
        String ID = id.getIdCode();
        FontMetrics fm6 = g.getFontMetrics(ft5);
        int textWidth6 = fm6.stringWidth("ID code: " + ID);
        int x6 = (clip.width - textWidth6) / 2;
        g.drawString("ID code: " + ID,x6,520);
        
        String loopLeft = "Reward";
        for (int i = 1; i <= 3; i++) {
            g.setColor(Color.red);
            Font font = new Font("Serif", Font.BOLD, 12);      
            g.setFont(font);
            int a = (clip.width - 180)/2 - i*30;
            int b = 115 + i*14;
            while(b <= 285 - i*14) {
                g.drawString(loopLeft, a, b);
                b+=14;
            }
        }
        
        Font f2 = new Font("Serif", Font.BOLD, 12);
        g.setFont(f2);
        FontMetrics fms = g.getFontMetrics(f2);
        String loopRight = "$" + reward;
        int looprightWidth = fms.stringWidth(loopLeft);
        for (int i = 1; i <= 3; i++) {
            Font font = new Font("Serif", Font.BOLD, 12);      
            g.setFont(font);
            int a = (clip.width + 180)/2 + i*30 - looprightWidth;
            int b = 115 + i*14;
            while (b <= 285 - i *14) {
                g.drawString(loopRight, a, b);
                b+=14;
            } 
        }
        
        g.setColor(Color.red);
        g.drawLine((clip.width - 150)/2, 95, (clip.width + 150)/2, 295);
        g.drawLine((clip.width - 150)/2, 295,(clip.width + 150)/2, 95);
        g.fillOval((clip.width - 10)/2, 190, 10, 10);
        
       
      
        // Complete this method
    }
    
    // Getters, do not need to change
     @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;

    }
}

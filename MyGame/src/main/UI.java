package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Obj_key;
public class UI {

    GamePanel gp;

    Font font, fontEnd;
    BufferedImage keyImage;

    public String text = "";
    public boolean textOn = false;
    public boolean gameEnd = false;
    public boolean death = false;
    public int counter = 0;
    public double time = 0.0;
    DecimalFormat df = new DecimalFormat("#0.00");
    public UI(GamePanel gp) {
        this.gp = gp;
        this.font = new Font("font", Font.PLAIN, 35);
        this.fontEnd = new Font("fontEnd", Font.BOLD, 65);
        Obj_key key = new Obj_key();
        keyImage = key.image1;
    }

    public void displayText(String text) {
        this.text = text;
        textOn = true;
    }

    public void draw(Graphics2D g2) {

        if(gameEnd) {

            g2.setFont(fontEnd);
            g2.setColor(Color.yellow);
            g2.drawString("CONGRATS!", gp.screenWidth/2 - 190, gp.screenWidth/2 - 25);
            g2.drawString("YOU ESCAPED", gp.screenWidth/2 - 230, gp.screenWidth/2 + 35);

            g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawString("Time taken: " + df.format(time) + "!", gp.tileSize * 5, gp.tileSize * 10);

            gp.gameThread = null;

        } else if(death) {

            g2.setFont(fontEnd);
            g2.setColor(Color.red);
            g2.drawString("YOU DIED!", gp.screenWidth/2 - 155, gp.screenWidth/2 - 15);

            g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawString("Don't get close to the people!", gp.tileSize * 4 - 6, gp.tileSize * 9);

            gp.gameThread = null;

        } else {
            g2.setFont(font);
            g2.setColor(Color.cyan);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            if(gp.player.keyNum){
                g2.drawString(" key found!", 75, 60);
            } else {
                g2.drawString(" no keys :(", 75, 60);
            }

            time += 1.0/60;
            g2.drawString("Time: " + df.format(time), gp.tileSize * 7, 60);

            if(textOn) {
                g2.setFont(g2.getFont().deriveFont(25F));
                g2.drawString(text, 20, 150);

                counter++;

                if(counter > 120) {
                    counter = 0;
                    textOn = false;
                }
            }
        }

    }
}

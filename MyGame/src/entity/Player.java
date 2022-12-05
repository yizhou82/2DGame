package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefault();
        getPlayerImage();
    }

    public void setDefault() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_up1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_up2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_down1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_down2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_right2.png"));
            still1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_still1.png"));
            still2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/orb_still2.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        //Add else to each statement to stop diagonal motion
        //It may interfere with animations
        if(keyH.upPressed == true) {
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
            x += speed;
        } else {
            direction = "still";
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
            case "still":
                if(spriteNum == 1) {
                    image = still1;
                }
                if(spriteNum == 2) {
                    image = still2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}

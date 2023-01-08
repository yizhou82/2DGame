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

    public final int screenX;
    public final int screenY;

    public boolean keyNum = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        hitBox = new Rectangle(3 * gp.scale, 3 * gp.scale, 8 * gp.scale, 8 * gp.scale);

        initialAreaX = 4;
        initialAreaY = 3;

        setDefault();
        getPlayerImage();
    }

    public void setDefault() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
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
        }
        else if(keyH.downPressed == true) {
            direction = "down";
        }
        else if(keyH.leftPressed == true) {
            direction = "left";
        }
        else if(keyH.rightPressed == true) {
            direction = "right";
        } else {
            direction = "still";
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        int objIndex = gp.cChecker.checkObject(this, true);
        pickupObj(objIndex);

        if(collisionOn == false) {
            if(keyH.shiftPressed) {
                speed = 6;
            } else {
                speed = 4;
            }
            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
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

    public void pickupObj(int i) {
        if(i != 10) {
            String objName = gp.obj[i].name;

            switch(objName) {
                case "key":
                    keyNum = true;
                    gp.obj[i] = null;
                    break;
                case "lock":
                    if(keyNum) {
                        gp.obj[i] = null;
                        keyNum = false;
                        gp.ui.displayText("Opened lock!");
                    } else {
                        gp.ui.displayText("Need key!");
                    }
                    break;
                case "chest":
                    gp.ui.gameEnd = true;
                    break;
                case "person":
                    gp.ui.death = true;
                    break;

            }
        }
    }

    public void draw(Graphics2D g2) {

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}

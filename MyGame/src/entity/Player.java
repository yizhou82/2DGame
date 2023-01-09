package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * An instance of a player in the game
 */
public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public boolean keyNum = false;

    /**
     * A constructor that creates an instance of tileManager
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        hitBox = new Rectangle(11 * gp.scale, 6 * gp.scale, 8 * gp.scale, 8 * gp.scale);

        initialAreaX = 11;
        initialAreaY = 6;

        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 25;
        speed = 4;
        direction = "up";

        getPlayerImage();
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_right2.png")));
            still1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_still1.png")));
            still2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/orb_still2.png")));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the player
     */
    public void update() {

        //Add else to each statement to stop diagonal motion
        //Note: it may interfere with animations of player

        if(keyH.upPressed) {
            direction = "up";
        }
        else if(keyH.downPressed) {
            direction = "down";
        }
        else if(keyH.leftPressed) {
            direction = "left";
        }
        else if(keyH.rightPressed) {
            direction = "right";
        } else {
            direction = "still";
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        int objIndex = gp.cChecker.checkObject(this, true);
        InteractObj(objIndex);

        if(!collisionOn) {
            if(keyH.shiftPressed) {
                speed = 6;
            } else {
                speed = 4;
            }
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        //spriteCounter and spriteNum allows the player's movement to switch between 2 images,
        //making the movement more lively

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

    /**
     * Interacts with an object
     * @param i the index of the object to be interacted with
     */
    public void InteractObj(int i) {
        if(i != 10) {
            String objName = gp.obj[i].name;

            switch (objName) {
                case "key" -> {
                    keyNum = true;
                    gp.obj[i] = null;
                }
                case "lock" -> {
                    if (keyNum) {
                        gp.obj[i] = null;
                        keyNum = false;
                        gp.ui.displayText("Opened lock!");
                    } else {
                        gp.ui.displayText("Need key!");
                    }
                }
                case "chest" -> gp.ui.gameEnd = true;
                case "person" -> gp.ui.death = true;
            }
        }
    }
    /**
     * Draws the player in a game
     * @param g2 a graphics2D object used to draw the player
     */
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "still" -> {
                if (spriteNum == 1) {
                    image = still1;
                }
                if (spriteNum == 2) {
                    image = still2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}

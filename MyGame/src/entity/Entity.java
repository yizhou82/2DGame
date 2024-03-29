package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An instance of an Entity in the game
 */
public class Entity {

    // Set player's default position
    // In java (0,0) starts in the top left corner of the screen, so
    // x values increase as they go right
    // y values increase as they go down
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, still1, still2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitBox;
    public int initialAreaX, initialAreaY;

    public boolean collisionOn = false;
}

package entity;

import java.awt.image.BufferedImage;

public class Entity {

    // Set player's default position
    // In java (0,0) starts at the top left corder of the screen, so
    // x values increase as they go right
    // y values increase as they go down
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, still1, still2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;


}

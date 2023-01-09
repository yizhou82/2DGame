package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An instance of an object where a player can interact with in the game.
 */
public class SuperObject {

    public int worldX, worldY;
    public BufferedImage image1, image2;
    public boolean collision = false;
    public String name;
    public Rectangle Area = new Rectangle(0, 0, 48,48);
    public int initialAreaX = 0, initialAreaY = 0;
    public boolean animate = false;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    /**
     * Draws the objects in a game
     * @param g2 a graphics2D object used to draw the objects
     */
    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        //Boundary of display, i.e. only draw image if the object is on the screen
        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
            if(this.animate) {
                if(spriteNum == 1) {
                    g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                if(spriteNum == 2) {
                    g2.drawImage(image2, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            } else {
                g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

        }
    }

    public void update() {

        //spriteCounter and spriteNum allows the object's movement to switch between 2 images,
        //making the object more lively

        spriteCounter++;
        if(spriteCounter > 36) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

}

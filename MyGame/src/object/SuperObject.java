package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public int worldX, worldY;
    public BufferedImage image1, image2;
    public boolean collision = false;
    public String name;

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        //Boundary
        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
                worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
                worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
            g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}

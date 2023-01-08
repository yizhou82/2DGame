package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//Named chest for future uses; for now, it's portal
public class Obj_chest extends SuperObject{
    public Obj_chest() {
        this.name = "chest";
        this.animate = true;

        try{
            image1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/portal1.png"));
            image2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/portal2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

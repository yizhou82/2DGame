package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Obj_person extends SuperObject{

    public Obj_person() {
        this.name = "person";
        this.animate = true;

        try{
            image1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/person1.png"));
            image2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/person2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

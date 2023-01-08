package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;

public class Obj_key extends SuperObject {
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Obj_key() {
        this.name = "key";

        Random random = new Random();

        try{
            if(random.nextBoolean()) {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key_1.png"));
            } else {
                image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key_2.png"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

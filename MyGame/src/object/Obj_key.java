package object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Obj_key extends SuperObject {

    public Obj_key() {
        this.name = "key";

        try{
            image1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key_1.png"));
            image2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

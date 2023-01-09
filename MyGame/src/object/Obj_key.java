package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * An instance of a key object
 */
public class Obj_key extends SuperObject {

    public Obj_key() {
        this.name = "key";
        this.animate = true;

        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/key_1.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/key_2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

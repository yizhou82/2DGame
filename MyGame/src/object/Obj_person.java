package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * An instance of a person object
 */
public class Obj_person extends SuperObject{

    public Obj_person() {
        this.name = "person";
        this.animate = true;

        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/person1.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/person2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

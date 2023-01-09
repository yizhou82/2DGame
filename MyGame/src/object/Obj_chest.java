package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * An instance of a chest object
 * Note: Named chest for future uses; for now, game uses portal
 */
public class Obj_chest extends SuperObject{

    public Obj_chest() {
        this.name = "chest";
        this.animate = true;

        try{
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/portal1.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("objects/portal2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

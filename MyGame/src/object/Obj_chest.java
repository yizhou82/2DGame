package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_chest extends SuperObject{
    public Obj_chest() {
        this.name = "chest";

        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

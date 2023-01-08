package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_lock extends SuperObject{


    public Obj_lock() {
        this.name = "lock";
        collision = true;

        try{
            image1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/lock.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}

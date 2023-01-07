package main;

import object.Obj_chest;
import object.Obj_key;
import object.Obj_lock;

public class ObjectPlacer {
    GamePanel gp;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Obj_key();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 35 * gp.tileSize;

        gp.obj[1] = new Obj_key();
        gp.obj[1].worldX = 19 * gp.tileSize;
        gp.obj[1].worldY = 14 * gp.tileSize;

        gp.obj[2] = new Obj_lock();
        gp.obj[2].worldX = 26 * gp.tileSize;
        gp.obj[2].worldY = 33 * gp.tileSize;

        gp.obj[3] = new Obj_lock();
        gp.obj[3].worldX = 40 * gp.tileSize;
        gp.obj[3].worldY = 37 * gp.tileSize;

        gp.obj[4] = new Obj_chest();
        gp.obj[4].worldX = 40 * gp.tileSize;
        gp.obj[4].worldY = 36 * gp.tileSize;


    }
}

package main;

import object.Obj_key;

public class ObjectPlacer {
    GamePanel gp;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new Obj_key();
        gp.obj[0].worldX = 40 * gp.tileSize;
        gp.obj[0].worldY = 36 * gp.tileSize;

        gp.obj[1] = new Obj_key();
        gp.obj[1].worldX = 19 * gp.tileSize;
        gp.obj[1].worldY = 14 * gp.tileSize;
    }
}

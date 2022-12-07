package main;

import entity.Entity;

public class Collision {

    GamePanel gp;

    public Collision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int ent_XLeft = entity.worldX + entity.hitBox.x;
        int ent_XRight = ent_XLeft + entity.hitBox.width;
        int ent_YTop = entity.worldY + entity.hitBox.y;
        int ent_YBottom = ent_YTop + entity.hitBox.height;

        int ent_LCol = ent_XLeft/gp.tileSize;
        int ent_RCol = ent_XRight/gp.tileSize;
        int ent_TRow = ent_YTop/gp.tileSize;
        int ent_BRow = ent_YBottom/gp.tileSize;

//        System.out.println("Left " + ent_LCol + ", Right "
//                           + ent_RCol + ", Up " + ent_TRow  + ", Down " + ent_BRow);

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                ent_TRow = (ent_YTop - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTiles[ent_TRow][ent_LCol];
                tileNum2 = gp.tileM.mapTiles[ent_TRow][ent_RCol];
                if(gp.tileM.tile[tileNum1].collision == true ||
                        gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                ent_BRow = (ent_YBottom + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTiles[ent_BRow][ent_LCol];
                tileNum2 = gp.tileM.mapTiles[ent_BRow][ent_RCol];
                if(gp.tileM.tile[tileNum1].collision == true ||
                        gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                ent_LCol = (ent_XLeft - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTiles[ent_TRow][ent_LCol];
                tileNum2 = gp.tileM.mapTiles[ent_BRow][ent_LCol];
                if(gp.tileM.tile[tileNum1].collision == true ||
                        gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                ent_RCol = (ent_XRight + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTiles[ent_TRow][ent_RCol];
                tileNum2 = gp.tileM.mapTiles[ent_BRow][ent_RCol];
                if(gp.tileM.tile[tileNum1].collision == true ||
                        gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }

    }
}

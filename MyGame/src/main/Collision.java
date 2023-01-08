package main;

import entity.Entity;
import object.SuperObject;

import java.awt.*;

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

    public int checkObject(Entity entity, boolean player) {

        int index = 10;
        int objNum = 0;

        for(SuperObject obj: gp.obj) {
            if(obj != null) {

                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;

                obj.Area.x += obj.worldX;
                obj.Area.y += obj.worldY;

                switch(entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.speed;
                        if(entity.hitBox.intersects(obj.Area)) {
                            if(obj.collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = objNum;
                            }
                        }

                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;
                        if(entity.hitBox.intersects(obj.Area)) {
                            if(obj.collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = objNum;
                            }
                        }

                        break;
                    case "left":
                        entity.hitBox.x -= entity.speed;
                        if(entity.hitBox.intersects(obj.Area)) {
                            if(obj.collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = objNum;
                            }
                        }

                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;
                        if(entity.hitBox.intersects(obj.Area)) {
                            if(obj.collision) {
                                entity.collisionOn = true;
                            }
                            if(player) {
                                index = objNum;
                            }
                        }

                        break;
                }

                entity.hitBox.x = entity.initialAreaX;
                entity.hitBox.y = entity.initialAreaY;

                obj.Area.x = obj.initialAreaX;
                obj.Area.y = obj.initialAreaY;


            }
            objNum++;
        }
        return index;
    }
}

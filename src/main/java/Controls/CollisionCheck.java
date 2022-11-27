package Controls;

import Entity.Entity;
import GUI.GameScene;
import GUI.TileManager;
import GUI.Window;
import Variables.Constant;
import Entity.Bomb;
import java.awt.*;
import java.util.ArrayList;

import Entity.Player;
public class CollisionCheck {
    TileManager tileM = new TileManager();
    private boolean flag = false;
    public CollisionCheck() {
    }

    public void checkTile(Entity entity) {
        entity.setEntityInteractionBox(entity);
        int entityLeftCol = entity.InteractionBox.get(3) / (Constant.original_tile_size * Constant.scale);
        int entityRightCol = entity.InteractionBox.get(1) / (Constant.original_tile_size * Constant.scale);
        int entityTopRow = entity.InteractionBox.get(0) / (Constant.original_tile_size * Constant.scale);
        int entityBottomRow = entity.InteractionBox.get(2) / (Constant.original_tile_size * Constant.scale);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entity.InteractionBox.get(0) - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityTopRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entity.InteractionBox.get(2) + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entity.InteractionBox.get(3) - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entity.InteractionBox.get(1) + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (Window.getWindow().tileM.tiles[tileNum1].collision
                        || Window.getWindow().tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }

    public void checkMob(Entity entity, Entity entity1) {
        entity.setEntityInteractionBox(entity);
        entity1.setEntityInteractionBox(entity1);
        Rectangle entitySolidBox = new Rectangle(entity.InteractionBox.get(3),
                entity.InteractionBox.get(0),
                entity.solidArea.width,
                entity.solidArea.height);
        Rectangle entity1SolidBox = new Rectangle(entity1.InteractionBox.get(3),
                entity1.InteractionBox.get(0),
                entity1.solidArea.width,
                entity1.solidArea.height);
        boolean intersects = entitySolidBox.intersects(entity1SolidBox);
        if (intersects) {
            entity.collisionOn = true;
            entity.state = 0;
        }
    }

    public int checkObject(Entity entity, Boolean player) {
        int index = 999;
        for (int i = 0; i < GameScene.Object.length; i++) {
            if (GameScene.Object[i] != null) {
                //get entity's solid area position
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;
                //get object's solid area position
                GameScene.Object[i].solidArea.x = GameScene.Object[i].x + GameScene.Object[i].solidArea.x;
                GameScene.Object[i].solidArea.y = GameScene.Object[i].y + GameScene.Object[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)) {
                            if (GameScene.Object[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)) {
                            if (GameScene.Object[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)) {
                            if (GameScene.Object[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(GameScene.Object[i].solidArea)) {
                            if (GameScene.Object[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                GameScene.Object[i].solidArea.x = GameScene.Object[i].solidAreaDefaultX;
                GameScene.Object[i].solidArea.y = GameScene.Object[i].solidAreaDefaultY;
            }
        }
        return index;
    }
   //check if player hit bomb
   public void checkBomb(ArrayList<Bomb> bombList,Player player) {
        if(bombList != null){
            for (int i = 0; i < bombList.size(); i++) {
                bombList.get(i).setEntityInteractionBox(bombList.get(i));
                player.setEntityInteractionBox(player);
                Rectangle bombSolidBox = new Rectangle(bombList.get(i).solidArea.x,
                        bombList.get(i).solidArea.y,
                        bombList.get(i).solidArea.width,
                        bombList.get(i).solidArea.height);
                Rectangle playerSolidBox = new Rectangle(player.solidArea.x,
                        player.solidArea.y,
                        player.solidArea.width,
                        player.solidArea.height);
                boolean intersects = bombSolidBox.intersects(playerSolidBox);
                if (intersects) {
                    player.collisionOn = true;
                    flag = true;
                    System.out.println("hit");
                    
                } else {
                    player.collisionOn = false;
                }
            }
                
                
            }
        }
    }
        
    
        
   
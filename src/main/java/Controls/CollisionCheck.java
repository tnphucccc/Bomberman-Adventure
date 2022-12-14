package Controls;

import Entity.Entity;
import GUI.*;
import Entity.*;
import Variables.Constant;
import java.awt.*;
import java.util.ArrayList;

public class CollisionCheck {
    public static CollisionCheck instance;

    public static CollisionCheck getInstance(){
        if(instance == null){
            instance = new CollisionCheck();
        }
        return instance;
    }

    public void checkTile(Entity entity) {
        entity.setEntityInteractionBox(entity);

        int entityLeftCol = entity.InteractionBox.get(3) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);
        int entityRightCol = entity.InteractionBox.get(1) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);
        int entityTopRow = entity.InteractionBox.get(0) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);
        int entityBottomRow = entity.InteractionBox.get(2) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);

        int tileNum1, tileNum2, tileNum3;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entity.InteractionBox.get(0) - entity.speed) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);

                tileNum1 = TileManager.getInstance().mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = TileManager.getInstance().mapTileNum[entityTopRow][entityRightCol];
                tileNum3 = TileManager.getInstance().mapTileNum[entityTopRow][entityLeftCol];


                if (TileManager.getInstance().tiles[tileNum1].collision
                        || TileManager.getInstance().tiles[tileNum2].collision){
                    entity.collisionOn = true;
                } else if (TileManager.getInstance().tiles[tileNum3].death){
                    entity.state = 0;
                }
            }
            case "down" -> {
                entityBottomRow = (entity.InteractionBox.get(2) + entity.speed) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);

                tileNum1 = TileManager.getInstance().mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = TileManager.getInstance().mapTileNum[entityBottomRow][entityRightCol];
                tileNum3 = TileManager.getInstance().mapTileNum[entityBottomRow][entityRightCol];


                if (TileManager.getInstance().tiles[tileNum1].collision
                        || TileManager.getInstance().tiles[tileNum2].collision){
                    entity.collisionOn = true;
                } else if (TileManager.getInstance().tiles[tileNum3].death){
                    entity.state = 0;
                }

            }
            case "left" -> {
                entityLeftCol = (entity.InteractionBox.get(3) - entity.speed) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);

                tileNum1 = TileManager.getInstance().mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = TileManager.getInstance().mapTileNum[entityBottomRow][entityLeftCol];
                tileNum3 = TileManager.getInstance().mapTileNum[entityBottomRow][entityLeftCol];

                if (TileManager.getInstance().tiles[tileNum1].collision
                        || TileManager.getInstance().tiles[tileNum2].collision){
                    entity.collisionOn = true;
                } else if (TileManager.getInstance().tiles[tileNum3].death){
                    entity.state = 0;
                }

            }
            case "right" -> {
                entityRightCol = (entity.InteractionBox.get(1) + entity.speed) / (Constant.ORIGINAL_TILE_SIZE * Constant.SCALE);

                tileNum1 = TileManager.getInstance().mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = TileManager.getInstance().mapTileNum[entityBottomRow][entityRightCol];
                tileNum3 = TileManager.getInstance().mapTileNum[entityBottomRow][entityRightCol];

                if (TileManager.getInstance().tiles[tileNum1].collision
                        || TileManager.getInstance().tiles[tileNum2].collision){
                    entity.collisionOn = true;
                } else if (TileManager.getInstance().tiles[tileNum3].death){
                    entity.state = 0;
                }
            }
        }
    }

    public void checkMob(Entity entity, ArrayList<Mob> mobList) {
        entity.setEntityInteractionBox(entity);
        Rectangle entitySolidBox = new Rectangle(entity.InteractionBox.get(3),
                entity.InteractionBox.get(0),
                entity.solidArea.width,
                entity.solidArea.height);
        if(mobList != null){
            for (Mob mob : mobList) {
                mob.setEntityInteractionBox(mob);
                Rectangle mobSolidBox = new Rectangle(mob.InteractionBox.get(3),
                        mob.InteractionBox.get(0),
                        mob.solidArea.width,
                        mob.solidArea.height);
                boolean intersects = entitySolidBox.intersects(mobSolidBox);
                if (intersects) {
                    entity.collisionOn = true;
                    entity.state = 0;
                    entity.speed = 0;
                }
            }
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
    public Rectangle check(int x, int y, int width, int height) {
        return new Rectangle(x,y,width,height);
    }
   //check if player hit bomb
   public void checkBomb(ArrayList<Bomb> bombList,Entity entity) {
        if(bombList != null) {
            for (Bomb bomb : bombList) {
                if (bomb.state != 2) {
                    bomb.setEntityInteractionBox(bomb);
                    entity.setEntityInteractionBox(entity);
                    Rectangle bombSolidBox = new Rectangle(bomb.solidArea.x + bomb.getX(),
                            bomb.solidArea.y + bomb.getY(),
                            bomb.solidArea.width,
                            bomb.solidArea.height);
                    Rectangle playerSolidBox = new Rectangle(entity.x,
                            entity.y,
                            entity.solidArea.width,
                            entity.solidArea.height);
                    boolean inter = bombSolidBox.intersects(playerSolidBox);
                    if (!inter) {
                        switch (entity.direction) {
                            case "up" -> {
                                Rectangle playerNextMove = check(entity.x, entity.y - entity.speed, entity.solidArea.width, entity.solidArea.height);
                                if (playerNextMove.intersects(bombSolidBox)) {
                                    entity.collisionOn = true;
                                    //    System.out.println("up");
                                    if (bomb.state == 1) {
                                        entity.state = 0;
                                    }
                                }
                            }
                            case "down" -> {
                                Rectangle playerNextMove = check(entity.x, entity.y + entity.speed, entity.solidArea.width, entity.solidArea.height);
                                if (playerNextMove.intersects(bombSolidBox)) {
                                    entity.collisionOn = true;
                                    //    System.out.println("down");
                                    if (bomb.state == 1) {
                                        entity.state = 0;
                                    }
                                }

                            }
                            case "left" -> {
                                Rectangle playerNextMove = check(entity.x - entity.speed, entity.y, entity.solidArea.width, entity.solidArea.height);
                                if (playerNextMove.intersects(bombSolidBox)) {
                                    entity.collisionOn = true;
                                    //    System.out.println("left");
                                    if (bomb.state == 1) {
                                        entity.state = 0;
                                    }
                                }

                            }
                            case "right" -> {
                                Rectangle playerNextMove = check(entity.x + entity.speed, entity.y, entity.solidArea.width, entity.solidArea.height);
                                if (playerNextMove.intersects(bombSolidBox)) {
                                    entity.collisionOn = true;
                                    //    System.out.println("right");
                                    if (bomb.state == 1) {
                                        entity.state = 0;
                                    }
                                }
                            }
                        }
                    }
                    if(bomb.state == 1){
                        // System.out.println(bomb.getX()/Constant.TILE_SIZE+" "+bomb.getY()/Constant.TILE_SIZE);
                        // System.out.println(entity.x+" "+entity.y);
                        if(entity.x/Constant.TILE_SIZE<=bomb.getX()/Constant.TILE_SIZE+bomb.getBombRadius() && entity.x/Constant.TILE_SIZE>=bomb.getX()/Constant.TILE_SIZE-bomb.getBombRadius() && entity.y/Constant.TILE_SIZE<=bomb.getY()/Constant.TILE_SIZE+bomb.getBombRadius() && entity.y/Constant.TILE_SIZE>=bomb.getY()/Constant.TILE_SIZE-bomb.getBombRadius()){
                                entity.state = 0;
                                System.out.println("hey");
                        }

                    }
                }
            }
        }
    }
}








        
    
        
   
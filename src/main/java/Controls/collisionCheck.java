package Controls;

import Entity.Entity;
import GUI.tileManager;
import Variables.Constant;
import Controls.collisionCheck;
import GUI.Window;

public class collisionCheck {
    tileManager tileM = new tileManager();
    public collisionCheck() {

    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / (Constant.original_tile_size * Constant.scale);
        int entityRightCol = entityRightX / (Constant.original_tile_size * Constant.scale);
        int entityTopRow = entityTopY / (Constant.original_tile_size * Constant.scale);
        int entityBottomRow = entityBottomY / (Constant.original_tile_size * Constant.scale);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityTopRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (tileM.tiles[tileNum1].collision
                        || tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / (Constant.original_tile_size * Constant.scale);
                tileNum1 = tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (Window.getWindow().tileM.tiles[tileNum1].collision
                        || Window.getWindow().tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
}

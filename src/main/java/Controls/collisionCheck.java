package Controls;

import Entity.Entity;
import GUI.gamePanel;

public class collisionCheck {
    gamePanel gamepanel;

    public collisionCheck(gamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / (gamepanel.original_tile_size * gamePanel.scale);
        int entityRightCol = entityRightX / (gamepanel.original_tile_size * gamePanel.scale);
        int entityTopRow = entityTopY / (gamepanel.original_tile_size * gamePanel.scale);
        int entityBottomRow = entityBottomY / (gamepanel.original_tile_size * gamePanel.scale);

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / (gamepanel.original_tile_size * gamePanel.scale);
                tileNum1 = gamepanel.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamepanel.tileM.mapTileNum[entityTopRow][entityRightCol];
                if (gamepanel.tileM.tiles[tileNum1].collision
                        || gamepanel.tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / (gamepanel.original_tile_size * gamePanel.scale);
                tileNum1 = gamepanel.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                tileNum2 = gamepanel.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gamepanel.tileM.tiles[tileNum1].collision
                        || gamepanel.tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / (gamepanel.original_tile_size * gamePanel.scale);
                tileNum1 = gamepanel.tileM.mapTileNum[entityTopRow][entityLeftCol];
                tileNum2 = gamepanel.tileM.mapTileNum[entityBottomRow][entityLeftCol];
                if (gamepanel.tileM.tiles[tileNum1].collision
                        || gamepanel.tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / (gamepanel.original_tile_size * gamePanel.scale);
                tileNum1 = gamepanel.tileM.mapTileNum[entityTopRow][entityRightCol];
                tileNum2 = gamepanel.tileM.mapTileNum[entityBottomRow][entityRightCol];
                if (gamepanel.tileM.tiles[tileNum1].collision
                        || gamepanel.tileM.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
}

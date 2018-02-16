/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DungeonCrawl.DungeonCrawl;
import java.util.HashSet;
import model.ButtonType;
import model.GameData;
import model.GameObject;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Collectible;
import model.Immoveable.Collectible.Key;
import model.Immoveable.Tile.Button;
import model.Immoveable.Tile.Lock;
import model.Immoveable.Tile.Tile;
import model.Immoveable.Tile.ToggleWall;
import model.Immoveable.Tile.Wall;
import model.LockType;
import model.Moveable.Monster;

/**
 *
 * @author russe_000
 */
public class Animator implements Runnable {

    public boolean running;
    private final int FRAMES_PER_SECOND = 15;

    @Override
    public void run() {
        running = true;

        while (running) {
            long startTime = System.currentTimeMillis();

            DungeonCrawl.gameData.update();
            processCollisions();
            DungeonCrawl.gamePanel.gameRender();
            DungeonCrawl.gamePanel.printScreen();
            DungeonCrawl.inventoryPanel.updateInventoryPanel();

            long endTime = System.currentTimeMillis();
            int sleepTime = (int) (1.0 / FRAMES_PER_SECOND * 1000)
                    - (int) (endTime - startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime); // ms
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void processCollisions() {
        for (GameObject object : GameData.gameObjects) {
            if (GameData.gamer.getCollisionBox().intersects(
                    object.getCollisionBox())) {
                GameData.gamer.collide(object);
                object.collide(GameData.gamer);

                        } else if (object instanceof ToggleWall) {
                            if (!((ToggleWall)object).isOpen()) {
                                GameData.gamer.noMove();
                            }
                        }
                    }
                    else if(object instanceof Button){
                            if(((Button)object).getType() == ButtonType.GREEN){
                                for(int count = 0; count < GameData.gameObjects.size(); count++){
                                    GameObject currObj = GameData.gameObjects.get(count);
                                    if(currObj instanceof ToggleWall){
                                        ((ToggleWall) currObj).setOpen(!((ToggleWall) currObj).isOpen());
                                        GameData.gameObjects.set(count, currObj);
                                    }
                                }
            }
        }
        //TODO: Handle Object on Object Violence
        
    }
}

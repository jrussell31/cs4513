/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DungeonCrawl.DungeonCrawl;
import java.util.ArrayList;
import model.GameData;
import static model.GameData.gameObjects;
import static model.GameData.gamerInventory;
import static model.GameData.killedMonsters;
import model.GameObject;
import model.Immoveable.Tile.Button;

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

        while (running && GameData.time > 0) {
            long startTime = System.currentTimeMillis();

            if (GameData.levelInProgress) {
                DungeonCrawl.gameData.update();
                processCollisions();
                DungeonCrawl.gamePanel.gameRender();
                DungeonCrawl.gamePanel.printScreen();
                DungeonCrawl.inventoryPanel.updateInventoryPanel();
            } else {
                DungeonCrawl.bannerPanel.setVisible(true);
                DungeonCrawl.gamePanel.requestFocus();
            }

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
            for (GameObject go : GameData.gameObjects) {
                if (object != go && object.getCollisionBox().intersects(
                        go.getCollisionBox())) {
                    //System.out.println(object.getClass() + " " + go.getClass());
                    object.collide(go);
                }
            }            
        }                                
    }
}

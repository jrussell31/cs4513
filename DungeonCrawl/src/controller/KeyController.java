/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DungeonCrawl.DungeonCrawl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Direction;
import model.GameData;
import model.GameObject;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class KeyController implements KeyListener {

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        GameObject firstGameObject = DungeonCrawl.gameData.gamer;

        if (firstGameObject instanceof Gamer) {
            Gamer gamer = (Gamer) firstGameObject;

            switch (ke.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    gamer.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    gamer.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    gamer.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    gamer.setDirection(Direction.DOWN);
                    break;
                case KeyEvent.VK_SPACE:
                    // If game hasn't started yet
                    if (!DungeonCrawl.thread.isAlive()) {
                        DungeonCrawl.bannerPanel.setVisible(false);
                        DungeonCrawl.startGame();
                    } 
                    // If game already started
                    // If the level is not in progress (End of Level)
                    else if (!GameData.levelInProgress) {
                        GameData.resetGameData();
                        DungeonCrawl.bannerPanel.setVisible(false);
                    }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        Object firstGameObject = DungeonCrawl.gameData.gamer;

        if (firstGameObject instanceof Gamer) {
            Gamer gamer = (Gamer) firstGameObject;
            gamer.setDirection(Direction.NONE);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DungeonCrawl.DungeonCrawl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Moveable.Gamer;

/**
 *
 * @author russe_000
 */
public class KeyController implements KeyListener  {

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Gamer gamer = DungeonCrawl.gameData.gamer;
        
        switch (ke.getKeyCode()) 
        {
            case KeyEvent.VK_LEFT:
                gamer.setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                gamer.setRight(true);
                break;
            case KeyEvent.VK_UP:
                gamer.setUp(true);
                break;
            case KeyEvent.VK_DOWN:
                gamer.setDown(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        Gamer gamer = DungeonCrawl.gameData.gamer;
        switch (ke.getKeyCode()){
            case KeyEvent.VK_LEFT:
                gamer.setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamer.setRight(false);
                break;
            case KeyEvent.VK_DOWN:
                gamer.setDown(false);
                break;
            case KeyEvent.VK_UP:
                gamer.setUp(false);
                break;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Moveable;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import model.GameData;
import model.GameObject;
import DungeonCrawl.DungeonCrawl;

/**
 *
 * 
 */
public abstract class Monster extends MoveableObject {
    public boolean isDisplayed = true;
    public float dx;
    public float dy;
    public Monster(float x, float y) {
        super(x, y);
        
    }

    public abstract void render(Graphics2D g);

    public boolean isAlive(){
        return super.isAlive(); 
    }
    
    public void noMove() {
        super.x = dx;
        super.y = dy;
    }
    
    public abstract void update();
    
    @Override
    public void collide(GameObject O){
        if(O instanceof Gamer){
            this.setAlive(false);
            ((Gamer)O).setAlive(false);
            DungeonCrawl.bannerPanel.setBannerText("You collided with a " + getClass().getSimpleName() + " on Level  " + GameData.currentLevel.getLevelValue());
            GameData.levelInProgress = false;
        }
    }
}

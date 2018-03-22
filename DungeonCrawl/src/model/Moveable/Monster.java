package model.Moveable;

import model.GameData;
import model.GameObject;
import DungeonCrawl.DungeonCrawl;
import model.Collidable;

public abstract class Monster extends MoveableObject implements Collidable {
    public boolean isDisplayed = true;
    public Monster(float x, float y) {
        super(x, y);
        
    }

    @Override
    public boolean isAlive(){
        return super.isAlive(); 
    }        
    
    @Override
    public void collide(GameObject O){
        if(O instanceof Gamer){
            this.setAlive(false);
            ((Gamer)O).setAlive(false);
            DungeonCrawl.bannerPanel.setBannerText("You collided with a " + getClass().getSimpleName() + " on Level  " + GameData.currentLevel.getLevelValue());
        }
    }
}

package model;

import java.util.ArrayList;
import model.Moveable.Gamer;

public abstract class Level {
    int levelValue;
    int levelTime;
    int levelChipCount;
    Gamer gamer;
    ArrayList<GameObject> immovableObjects;
    ArrayList<GameObject> moveableObjects;
    
    public static boolean fLevelOne, fLevelTwo, fLevelThree = false; 
    
    public Level() {
        this.immovableObjects = new ArrayList<>();
        this.moveableObjects = new ArrayList<>();
    }
    
    public abstract void setGamer();
    
    public abstract void setImmovableObjects();
    
    public abstract void setMovableObjects();
    
    public abstract void setLevelValue();
    
    public abstract void setLevelTime();
    
    public abstract void setLevelChipCount();
    
    public abstract void resetLevel();
    
    public Gamer getGamer(){
        return gamer;
    }
    
    public ArrayList<GameObject> getImmovableObjects(){
        return immovableObjects;
    }
    
    public ArrayList<GameObject> getMoveableObjects(){
        return moveableObjects;
    }
    
    public int getLevelValue(){
        return levelValue;
    }
    
    public int getLevelTime(){
        return levelTime;
    }
    
    public int getLevelChipCount(){
        return levelChipCount;
    }
}

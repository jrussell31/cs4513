package model;

import java.util.ArrayList;
import static model.GameData.MAP_HEIGHT;
import static model.GameData.MAP_WIDTH;
import model.Immoveable.Tile.Wall;
import model.Moveable.Gamer;

public final class LevelTwo extends Level {
    
    public LevelTwo(){
        resetLevel();
    }

    @Override
    public void setGamer(){
        super.gamer = new Gamer(1, 1);
    }

    @Override
    public void setImmovableObjects() {   
        super.immovableObjects = new ArrayList<>();
        
        //Walls
        for(int i = 0; i <= MAP_WIDTH; ++i){
            super.immovableObjects.add(new Wall(i, 0));
            super.immovableObjects.add(new Wall(i, MAP_HEIGHT));
        }
        for(int i = 0; i <= MAP_HEIGHT; ++i){
            super.immovableObjects.add(new Wall(0, i));
            super.immovableObjects.add(new Wall(MAP_WIDTH, i));
        }
    }

    @Override
    public void setMovableObjects() {
        super.moveableObjects = new ArrayList<>();
    }

    @Override
    public void setLevelValue() {
        super.levelValue = 2;
    }

    @Override
    public void setLevelTime() {
        super.levelTime = 120;
    }

    @Override
    public void setLevelChipCount() {
        super.levelChipCount = 0;
    }

    @Override
    public void resetLevel() {
        setGamer();
        setImmovableObjects();
        setMovableObjects();
        setLevelValue();
        setLevelTime();
        setLevelChipCount();
    }
    
}

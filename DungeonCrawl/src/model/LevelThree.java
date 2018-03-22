package model;

import java.util.ArrayList;
import static model.GameData.MAP_HEIGHT;
import static model.GameData.MAP_WIDTH;
import model.Immoveable.Tile.Ice;
import model.Immoveable.Tile.Wall;
import model.Moveable.Ball;
import model.Moveable.Block;
import model.Moveable.Bug;
import model.Moveable.Fireball;
import model.Moveable.Frog;
import model.Moveable.Gamer;
import model.Moveable.Glider;
import model.Moveable.Tank;
import model.Moveable.Walker;

public final class LevelThree extends Level {
    
    public LevelThree(){
        resetLevel();
    }

    @Override
    public void setGamer(){
        super.gamer = new Gamer(28, 4);
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
        
        for(int i = 1; i <= MAP_HEIGHT; ++i){
            super.immovableObjects.add(new Wall(13, i));
        }
        
        //left ice
        for(int i = 5; i < 10; ++i){
            super.immovableObjects.add(new Ice(i, 4));
        }        
        super.immovableObjects.add(new Ice(10, 4, Direction.NE));
        super.immovableObjects.add(new Ice(10, 5));
        super.immovableObjects.add(new Ice(10, 6, Direction.SE));
        super.immovableObjects.add(new Ice(9, 6));
        super.immovableObjects.add(new Ice(8, 6));
        super.immovableObjects.add(new Ice(7, 6, Direction.SW));
        super.immovableObjects.add(new Ice(7,5));
        super.immovableObjects.add(new Ice(7, 3, Direction.NW));        
        super.immovableObjects.add(new Ice(8, 3));
        super.immovableObjects.add(new Ice(9, 3));
        super.immovableObjects.add(new Ice(10, 3));
        super.immovableObjects.add(new Ice(11, 3, Direction.NW));
        
        for(int i = 5; i < 10; ++i){
            super.immovableObjects.add(new Ice(i, 9));
        }        
        super.immovableObjects.add(new Ice(10, 9, Direction.NE));
        super.immovableObjects.add(new Ice(10, 10));
        super.immovableObjects.add(new Ice(10, 11, Direction.SE));
        super.immovableObjects.add(new Ice(9, 11));
        super.immovableObjects.add(new Ice(8, 11));
        super.immovableObjects.add(new Ice(7, 11, Direction.SW));
        super.immovableObjects.add(new Ice(7,10));
        super.immovableObjects.add(new Ice(7, 8, Direction.NW));        
        super.immovableObjects.add(new Ice(8, 8));
        super.immovableObjects.add(new Ice(9, 8));
        super.immovableObjects.add(new Ice(10, 8));
        super.immovableObjects.add(new Ice(11, 8, Direction.NW));
        
        for(int i = 5; i < 10; ++i){
            super.immovableObjects.add(new Ice(i, 14));
        }        
        super.immovableObjects.add(new Ice(10, 14, Direction.NE));
        super.immovableObjects.add(new Ice(10, 15));
        super.immovableObjects.add(new Ice(10, 16, Direction.SE));
        super.immovableObjects.add(new Ice(9, 16));
        super.immovableObjects.add(new Ice(8, 16));
        super.immovableObjects.add(new Ice(7, 16, Direction.SW));
        super.immovableObjects.add(new Ice(7,15));
        super.immovableObjects.add(new Ice(7, 13, Direction.NW));        
        super.immovableObjects.add(new Ice(8, 13));
        super.immovableObjects.add(new Ice(9, 13));
        super.immovableObjects.add(new Ice(10, 13));
        super.immovableObjects.add(new Ice(11, 13, Direction.NW));
        
        for(int i = 5; i < 10; ++i){
            super.immovableObjects.add(new Ice(i, 19));
        }        
        super.immovableObjects.add(new Ice(10, 19, Direction.NE));
        super.immovableObjects.add(new Ice(10, 20));
        super.immovableObjects.add(new Ice(10, 21, Direction.SE));
        super.immovableObjects.add(new Ice(9, 21));
        super.immovableObjects.add(new Ice(8, 21));
        super.immovableObjects.add(new Ice(7, 21, Direction.SW));
        super.immovableObjects.add(new Ice(7, 20));
        super.immovableObjects.add(new Ice(7, 18, Direction.NW));        
        super.immovableObjects.add(new Ice(8, 18));
        super.immovableObjects.add(new Ice(9, 18));
        super.immovableObjects.add(new Ice(10, 18));
        super.immovableObjects.add(new Ice(11, 18, Direction.NW));
         
        //right ice
        for(int i = 18; i < 23; ++i){
            super.immovableObjects.add(new Ice(i, 4));
        }        
        super.immovableObjects.add(new Ice(23, 4, Direction.NE));
        super.immovableObjects.add(new Ice(23, 5));
        super.immovableObjects.add(new Ice(23, 6, Direction.SE));
        super.immovableObjects.add(new Ice(22, 6));
        super.immovableObjects.add(new Ice(21, 6));
        super.immovableObjects.add(new Ice(20, 6, Direction.SW));
        super.immovableObjects.add(new Ice(20,5));
        super.immovableObjects.add(new Ice(20, 3, Direction.NW));        
        super.immovableObjects.add(new Ice(21, 3));
        super.immovableObjects.add(new Ice(22, 3));
        super.immovableObjects.add(new Ice(23, 3));
        super.immovableObjects.add(new Ice(24, 3, Direction.NW));
        
        for(int i = 18; i < 23; ++i){
            super.immovableObjects.add(new Ice(i, 9));
        }        
        super.immovableObjects.add(new Ice(23, 9, Direction.NE));
        super.immovableObjects.add(new Ice(23, 10));
        super.immovableObjects.add(new Ice(23, 11, Direction.SE));
        super.immovableObjects.add(new Ice(22, 11));
        super.immovableObjects.add(new Ice(21, 11));
        super.immovableObjects.add(new Ice(20, 11, Direction.SW));
        super.immovableObjects.add(new Ice(20,10));
        super.immovableObjects.add(new Ice(20, 8, Direction.NW));        
        super.immovableObjects.add(new Ice(21, 8));
        super.immovableObjects.add(new Ice(22, 8));
        super.immovableObjects.add(new Ice(23, 8));
        super.immovableObjects.add(new Ice(24, 8, Direction.NW));
        
        for(int i = 18; i < 23; ++i){
            super.immovableObjects.add(new Ice(i, 14));
        }        
        super.immovableObjects.add(new Ice(23, 14, Direction.NE));
        super.immovableObjects.add(new Ice(23, 15));
        super.immovableObjects.add(new Ice(23, 16, Direction.SE));
        super.immovableObjects.add(new Ice(22, 16));
        super.immovableObjects.add(new Ice(21, 16));
        super.immovableObjects.add(new Ice(20, 16, Direction.SW));
        super.immovableObjects.add(new Ice(20,15));
        super.immovableObjects.add(new Ice(20, 13, Direction.NW));        
        super.immovableObjects.add(new Ice(21, 13));
        super.immovableObjects.add(new Ice(22, 13));
        super.immovableObjects.add(new Ice(23, 13));
        super.immovableObjects.add(new Ice(24, 13, Direction.NW));
        
        for(int i = 18; i < 23; ++i){
            super.immovableObjects.add(new Ice(i, 19));
        }        
        super.immovableObjects.add(new Ice(23, 19, Direction.NE));
        super.immovableObjects.add(new Ice(23, 20));
        super.immovableObjects.add(new Ice(23, 21, Direction.SE));
        super.immovableObjects.add(new Ice(22, 21));
        super.immovableObjects.add(new Ice(21, 21));
        super.immovableObjects.add(new Ice(20, 21, Direction.SW));
        super.immovableObjects.add(new Ice(20, 20));
        super.immovableObjects.add(new Ice(20, 18, Direction.NW));        
        super.immovableObjects.add(new Ice(21, 18));
        super.immovableObjects.add(new Ice(22, 18));
        super.immovableObjects.add(new Ice(23, 18));
        super.immovableObjects.add(new Ice(24, 18, Direction.NW));
    }

    @Override
    public void setMovableObjects() {
        super.moveableObjects = new ArrayList<>();
        
        super.moveableObjects.add(new Ball(4, 4,Direction.RIGHT));
        super.moveableObjects.add(new Bug(4, 9, Direction.RIGHT));
        super.moveableObjects.add(new Fireball(4, 14, Direction.RIGHT));
        super.moveableObjects.add(new Walker(4, 19, Direction.RIGHT));
        super.moveableObjects.add(new Frog(17, 4));
        super.moveableObjects.add(new Glider(17, 9, Direction.RIGHT));
        super.moveableObjects.add(new Tank(17, 14, Direction.RIGHT));
        super.moveableObjects.add(new Block(17, 19));
    }

    @Override
    public void setLevelValue() {
        super.levelValue = 3;
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

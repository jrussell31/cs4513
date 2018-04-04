package model;

import java.util.ArrayList;
import static model.GameData.MAP_HEIGHT;
import static model.GameData.MAP_WIDTH;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.FakeChip;
import model.Immoveable.Tile.Button;
import model.Immoveable.Tile.Fire;
import model.Immoveable.Tile.Ice;
import model.Immoveable.Tile.Portal;
import model.Immoveable.Tile.Teleporter;
import model.Immoveable.Tile.Trap;
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

    public LevelThree() {
        resetLevel();
    }

    @Override
    public void setGamer() {
        super.gamer = new Gamer(1, 1);
    }

    @Override
    public void setImmovableObjects() {
        super.immovableObjects = new ArrayList<>();

        //Walls
        for (int i = 0; i <= MAP_WIDTH; ++i) {
            super.immovableObjects.add(new Wall(i, 0));
            super.immovableObjects.add(new Wall(i, MAP_HEIGHT));
        }
        for (int i = 0; i <= MAP_HEIGHT; ++i) {
            super.immovableObjects.add(new Wall(0, i));
            super.immovableObjects.add(new Wall(MAP_WIDTH, i));
        }
        
        super.immovableObjects.add(new FakeChip(3,3));
        super.immovableObjects.add(new FakeChip(10,5));
        super.immovableObjects.add(new Fire(11,5));
        super.immovableObjects.add(new Fire(3,4));
        
        //Level Chips
        super.immovableObjects.add(new Chip(2, 2));
        super.immovableObjects.add(new Chip(2, 3));
        super.immovableObjects.add(new Chip(2, 4));
        //Portal 
        super.immovableObjects.add(new Portal(9, 2));
 }

    @Override
    public void setMovableObjects() {
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
        super.levelChipCount = 5;
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

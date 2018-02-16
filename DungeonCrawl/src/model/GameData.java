/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import model.Moveable.Gamer;
import model.Moveable.Monster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Immoveable.Tile.Wall;
import model.Moveable.Ball;
import model.Moveable.Fireball;
import model.Moveable.Tank;
import model.Immoveable.Collectible.Boot;
import model.Immoveable.Collectible.Chip;
import model.Immoveable.Collectible.Key;
import model.Immoveable.Tile.Button;
import model.Immoveable.Tile.Lock;
import model.Immoveable.Tile.Portal;
import model.Immoveable.Tile.ToggleWall;
import model.Moveable.Block;

/**
 *
 * @author russe_000
 */
public class GameData {
    public static List<GameObject> gameObjects;
    public static List<GameObject> gamerInventory;
    public static Gamer gamer;
    public static Key redKey, yellowKey;
    public static Lock blueLock, yellowLock, socket;
    public static Boot fireBoot, iceBoot;
    public static int level;
    public static Fireball fireball;
    public static Tank tank;
    public static Ball ball;
    public static Wall wall;
    public static Portal portal;
    public static int time;
    public static int chipsLeft;
    private int timerCounter;
    
    public GameData() 
    {
        gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
        gamerInventory = Collections.synchronizedList(new ArrayList<GameObject>());
        
        // Level specific items
        gamer = new Gamer(1, 1);
        fireball = new Fireball (354,321); 
        tank = new Tank (706, 321);
        ball = new Ball(642,289);
        GameData.gameObjects.add(GameData.fireball); 
        GameData.gameObjects.add(GameData.tank); 
        GameData.gameObjects.add(GameData.ball);
        
        GameData.level = 1;
        GameData.time = 120;
        timerCounter = 0;
        
        //Level Chips
        gameObjects.add(new Chip(4,3));
        gameObjects.add(new Chip(4,5));
        gameObjects.add(new Chip(3,4));
        gameObjects.add(new Chip(5,4));
        gameObjects.add(new Chip(19,17));
        GameData.chipsLeft = 5;
        
        //Level keys
        gameObjects.add(new Key(4, 4, LockType.BLUE));
        gameObjects.add(new Key(11, 13, LockType.GREEN));
        gameObjects.add(new Key(11, 17, LockType.RED));
        gameObjects.add(new Key(19, 13, LockType.BLUE));
        gameObjects.add(new Key(15, 21, LockType.YELLOW));
        
        //Level locks
        gameObjects.add(new Lock(8, 4, LockType.BLUE));
        gameObjects.add(new Lock(12, 13, LockType.RED));
        gameObjects.add(new Lock(12, 17, LockType.YELLOW));
        gameObjects.add(new Lock(18, 13, LockType.GREEN));
        gameObjects.add(new Lock(18, 17, LockType.BLUE));
        gameObjects.add(new Lock(15, 25, LockType.SOCKET));
        
        //Level blocks
        gameObjects.add(new Block(15, 3));
        
        //Level portal
        gameObjects.add(new Portal(15, 26));
        
        //Toggle wall tile
        ArrayList<GameObject> toggleWalls = new ArrayList<>();
        toggleWalls.add(new ToggleWall(13, 13, true));
        toggleWalls.add(new ToggleWall(13, 17, false));
        toggleWalls.add(new ToggleWall(17, 13, false));
        toggleWalls.add(new ToggleWall(17, 17, true));
        gameObjects.addAll(toggleWalls);
        
        //Buttons
        gameObjects.add(new Button(15, 11, ButtonType.GREEN, toggleWalls));
    }
    
    public void resetGameData()
    {
        GameData.gamer = new Gamer(1, 1);
        GameData.gamer.update();
    }
    
    public void collectChip(){
        --chipsLeft;
    }
    
    public void update() 
    {
        if(GameData.time > 0)
        {
            if(timerCounter == 10){
                timerCounter = 0;
                GameData.time--;
            }
            else{
                timerCounter++;
            }
        }
        
        gamer.update();
        
        synchronized(gameObjects)
        {
            for(GameObject object: gameObjects)
            {
                if(object instanceof Monster)
                {
                    ((Monster)object).update();
                    
                    // ((Monster)object).findCollision();
                }
            }
        }
        
        ArrayList<GameObject> removeInventory = new ArrayList<>();
        synchronized(gamerInventory){
            for(GameObject object : gamerInventory){
                if(!object.isAlive()){
                    removeInventory.add(object);
                }             
            }
        }
        gamerInventory.removeAll(removeInventory);
                       
    }
}

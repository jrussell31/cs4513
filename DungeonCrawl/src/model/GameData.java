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
        GameData.chipsLeft = 3;
        timerCounter = 0;
        
        //Level keys
        gameObjects.add(new Key(29, 1, LockType.GREEN));
        
        //Level locks
        gameObjects.add(new Lock(0, 1, LockType.BLUE));
        gameObjects.add(new Lock(30, 1, LockType.BLUE));
        for(int j = 0; j < 31; ++j){
            gameObjects.add(new Lock(j, 0, LockType.BLUE));
            if(j == 15) gameObjects.add(new Lock(j, 2, LockType.GREEN));
            else gameObjects.add(new Lock(j, 2, LockType.BLUE));
        }
        
        //Level blocks
        gameObjects.add(new Block(15, 3));
        
        socket = new Lock(16, 25, LockType.SOCKET);
        gameObjects.add(socket);
        
        portal = new Portal(16, 26); 
        gameObjects.add(portal);
        
        //Toggle wall tile
        ArrayList<GameObject> toggleWalls = new ArrayList<>();
        toggleWalls.add(new ToggleWall(14, 14, true));
        toggleWalls.add(new ToggleWall(14, 18, false));
        toggleWalls.add(new ToggleWall(18, 14, false));
        toggleWalls.add(new ToggleWall(18, 18, true));
        gameObjects.addAll(toggleWalls);
        
        //Green Button
        gameObjects.add(new Button(16, 11, ButtonType.GREEN, toggleWalls));
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

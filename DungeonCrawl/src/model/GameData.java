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
import model.Immoveable.Tile.Toggle;
import model.Moveable.Block;

/**
 *
 * @author russe_000
 */
public class GameData {
    public static final int MAP_WIDTH = 30;
    public static final int MAP_HEIGHT = 27;
    public static List<GameObject> gameObjects;
    public static List<GameObject> gamerInventory;
    public static Gamer gamer;
    public static Boot fireBoot, iceBoot;
    public static int level;
    public static Fireball fireball;
    public static Tank tank;
    public static Ball ball;
    public static Wall wall;
    public static int time;
    public static int chipsLeft;
    private int timerCounter;
    
    public GameData() 
    {
        gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
        gamerInventory = Collections.synchronizedList(new ArrayList<GameObject>());
                
        gamer = new Gamer(1, 1);
        
        GameData.level = 1;
        GameData.time = 120;
        GameData.chipsLeft = 4;
        timerCounter = 0;
        
        //Level monsters
        gameObjects.add(new Fireball(4, 2));
        gameObjects.add(new Fireball(4, 6));                
        
        //Level keys
        gameObjects.add(new Key(4, 4, LockType.BLUE));
        gameObjects.add(new Key(15, 21, LockType.YELLOW));
        gameObjects.add(new Key(11, 13, LockType.GREEN));
        gameObjects.add(new Key(11, 17, LockType.RED));
        gameObjects.add(new Key(19, 13, LockType.BLUE));
        
        //Level locks        
        gameObjects.add(new Lock(8, 4, LockType.BLUE));
        gameObjects.add(new Lock(12, 13, LockType.RED));
        gameObjects.add(new Lock(12, 17, LockType.YELLOW));
        gameObjects.add(new Lock(18, 13, LockType.GREEN));
        gameObjects.add(new Lock(18, 17, LockType.BLUE));
        gameObjects.add(new Lock(15, 25, LockType.SOCKET));
        
        //Level boots
        //gameObjects.add(new Boot(3, 3, BootType.FIRE));
        
        //Level Chips
        gameObjects.add(new Chip(4, 3));
        gameObjects.add(new Chip(4, 5));
        gameObjects.add(new Chip(3, 4));
        gameObjects.add(new Chip(5, 4));
        gameObjects.add(new Chip(19, 17));
        
        //Level blocks
        //gameObjects.add(new Block(15, 3));
        
        //Level buttons
        gameObjects.add(new Button(15, 10, ButtonType.GREEN));
        
        //Level walls
        //Toggle Walls
        gameObjects.add(new Toggle(13, 13, false));
        gameObjects.add(new Toggle(17, 13, true));
        gameObjects.add(new Toggle(13, 17, true));
        gameObjects.add(new Toggle(17, 17, false));
        
        //Exit Portal
        gameObjects.add(new Portal(15, 26));
        
        //border walls
        for(int i = 0; i <= MAP_WIDTH; ++i){
            gameObjects.add(new Wall(i, 0));
            gameObjects.add(new Wall(i, MAP_HEIGHT));
        }
        for(int i = 0; i <= MAP_HEIGHT; ++i){
            gameObjects.add(new Wall(0, i));
            gameObjects.add(new Wall(MAP_WIDTH, i));
        }        
        
        for(int i = 0; i <= 8; ++i){
            gameObjects.add(new Wall(i, 8));
            if(i != 4){
                gameObjects.add(new Wall(8, i));
            }
        }
        
        for(int i = 10; i <= 20; ++i){
            gameObjects.add(new Wall(8, i));
            gameObjects.add(new Wall(22, i));
        }
        for(int i = 9; i <= 14; ++i){
            gameObjects.add(new Wall(i, 10));
            gameObjects.add(new Wall(i, 20));
        }
        for(int i = 16; i <= 21; ++i){
            gameObjects.add(new Wall(i, 10));
            gameObjects.add(new Wall(i, 20));
        }
        
        for(int i = 12; i < 19; ++i){
            gameObjects.add(new Wall(10, i));
            gameObjects.add(new Wall(20, i));
        }
        
        for(int i = 11; i < 20; ++i){
            if(i != 15){
                gameObjects.add(new Wall(i, 12));
                gameObjects.add(new Wall(i, 18));  
                if(i != 14 && i != 16){
                    gameObjects.add(new Wall(i, 14));
                    gameObjects.add(new Wall(i, 16));
                }
            }            
        }
        
        gameObjects.add(new Wall(11, 15));
        gameObjects.add(new Wall(19, 15));
        
        gameObjects.add(new Wall(14, 8));
        gameObjects.add(new Wall(14, 9));
        gameObjects.add(new Wall(14, 21));
        gameObjects.add(new Wall(14, 22));
        gameObjects.add(new Wall(14, 25));
        gameObjects.add(new Wall(14, 26));
        
        gameObjects.add(new Wall(15, 22));
        gameObjects.add(new Wall(16, 8));
        gameObjects.add(new Wall(16, 9));
        gameObjects.add(new Wall(16, 21));
        gameObjects.add(new Wall(16, 22));
        gameObjects.add(new Wall(16, 25));
        gameObjects.add(new Wall(16, 26));
                
    }
    
    public void resetGameData()
    {
        GameData.gamer = new Gamer(1, 1);
        GameData.gamer.update();
    }
    
    public static void collectChip(){
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

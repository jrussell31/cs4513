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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author russe_000
 */
public class GameData {
    public static final int MAP_WIDTH = 30;
    public static final int MAP_HEIGHT = 27;
    public static List<GameObject> gameObjects;
    public static List<GameObject> gamerInventory;
    private static Map<LevelNumber, Level> gameLevels;
    public static Level currentLevel;
    public static int time;
    public static Gamer gamer;
    public static int chipsLeft;
    private static int timerCounter;
    public static boolean levelInProgress = false;
    
    public GameData() 
    {
        gameObjects = Collections.synchronizedList(new ArrayList<>());
        gamerInventory = Collections.synchronizedList(new ArrayList<>());
        
        // List of Levels
        gameLevels = new HashMap();
        gameLevels.put(LevelNumber.LEVELONE, new LevelOne());
        gameLevels.put(LevelNumber.LEVELTWO, new LevelTwo());
        
        currentLevel = gameLevels.get(LevelNumber.LEVELONE);
        
        resetGameData();
    }
    
    public static void resetGameData()
    {
        currentLevel.resetLevel();
        time = currentLevel.getLevelTime();
        chipsLeft = currentLevel.getLevelChipCount();
        timerCounter = 0;
        
        //Clear out game objects
        gameObjects.clear();
        gamerInventory.clear();
        
        //Immoveable Objects
        gameObjects.addAll(currentLevel.getMoveableObjects());
        
        //Moveable Objects
        gameObjects.addAll(currentLevel.getImmovableObjects());
        gamer = currentLevel.getGamer();
        
        //Gamer Object
        gamer.update();
        
        levelInProgress = true;
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
                }
                if(object instanceof Block){
                    ((Block)object).update();
                }
            }
        }
    }
    
    public static void goToNextLevel(){
        levelInProgress = false;
        
        //Logic for next level
        currentLevel = gameLevels.get(LevelNumber.LEVELTWO);
    }
}

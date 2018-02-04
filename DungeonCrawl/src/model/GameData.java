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
import model.Moveable.Fireball;

/**
 *
 * @author russe_000
 */
public class GameData {
    public static List<GameObject> gameObjects;
    public static Gamer gamer;
    public static int level;
    public static Fireball fireball; 
    public static int time;
    public static int chipsLeft;
    private int timerCounter;
    //public static Landscape landscape;

    public GameData() 
    {
        gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
        
        // Level specific items
        gamer = new Gamer(650, 625);
        fireball = new Fireball (750,725); 
        GameData.gameObjects.add(GameData.gamer);
        GameData.gameObjects.add(GameData.fireball); 
        GameData.level = 1;
        GameData.time = 120;
        GameData.chipsLeft = 10;
        timerCounter = 0;
    }
    
    public void resetGameData()
    {
        GameData.gamer = new Gamer(650, 625);
        GameData.gamer.update();
        
//        if(landscape.getLevel() instanceof YouWinLevel)
//        {
//            hero.hearts += 6;
//            
//            Main.metricPanel.updateHeartsAndArrows();
//        }
//        
//        landscape.setLevel(landscape.getLevel());
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
        
        synchronized(gameObjects)
        {
            for(GameObject object: gameObjects)
            {
                if(object instanceof Monster)
                {
                    ((Monster)object).update();
                    
                    // ((Monster)object).findCollision();
                }
                else if(object instanceof Gamer){
                    ((Gamer)object).update();
                }
            }
        }
    }
}

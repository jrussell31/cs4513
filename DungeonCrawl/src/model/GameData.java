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

/**
 *
 * @author russe_000
 */
public class GameData {
    public static List<GameObject> gameObjects;
    public static Gamer gamer;
    //public static Landscape landscape;

    public GameData() 
    {
        // landscape = new Landscape();
        gameObjects = Collections.synchronizedList(new ArrayList<GameObject>());
        gamer = new Gamer(650, 625);
    }
    
    public void resetGameData()
    {
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
    }
}

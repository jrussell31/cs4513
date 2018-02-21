/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author russe_000
 */
public interface GameObject {    
    public static float HEIGHT = 32, WIDTH = 32;
    public static int OFFSET = 32;
    public static int MOVEMENT = 32;

    
    public abstract void render(Graphics2D g);
    
    public abstract void update();
    
    public Rectangle2D.Double getCollisionBox();
    
    public abstract boolean isAlive();
    
    public abstract void collide(GameObject O);
    
    public abstract void slide();
}

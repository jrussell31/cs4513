/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import java.awt.Graphics2D;
import model.Direction;
import model.GameObject;

/**
 *
 * @author mfile
 */
public class Force extends Floor
{

    public Force(float x, float y) {
        super(x, y);
        super.direction = Direction.NONE;
    }

    @Override
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void collide(GameObject O) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

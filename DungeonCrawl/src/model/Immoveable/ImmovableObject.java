package model.Immoveable;

import java.awt.geom.Rectangle2D;
import model.GameObject;

public abstract class ImmovableObject implements GameObject {
    
    public float x, y, height = 32, width = 32;
    public static int offset = 32;
    private boolean alive;    
    
    public ImmovableObject(float x, float y){
        this.x = x * offset;
        this.y = y * offset;
        alive = true;
    }
    
    @Override
    public Rectangle2D.Double getCollisionBox(){
        return new Rectangle2D.Double(x, y, width, height);
    }
    
    @Override
    public boolean isAlive(){
        return alive;
    }
    
    public void setAlive(boolean a){
        this.alive = a;
    }
    
}

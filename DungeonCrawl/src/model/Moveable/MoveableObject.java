package model.Moveable;

import java.awt.geom.Rectangle2D;
import model.Direction;
import model.GameObject;

public abstract class MoveableObject implements GameObject {
    public float x, y;
    public Direction direction;
    private boolean alive;
    
    public MoveableObject(float x, float y){
        this.x = x * OFFSET;
        this.y = y * OFFSET;
        this.alive = true;
    }
    
    public void setAlive(boolean alive){
        this.alive = alive;
    }
    
    @Override
    public boolean isAlive(){
        return alive;
    }
    
    public abstract void update();
    
    @Override
    public Rectangle2D.Double getCollisionBox(){
        return new Rectangle2D.Double(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void slide(){
        switch(direction){
            case RIGHT:
                this.x += MOVEMENT;
        }
    }
}

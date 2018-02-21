/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Immoveable.Tile;

import controller.ImageFinder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.GameObject;

public class Toggle extends Wall{    
    private boolean close;
    public BufferedImage[] image;
    
    public Toggle(float x, float y, boolean c) {
        super(x, y);
        close = c;
        
        image = new BufferedImage[2];
        try{
            BufferedImage temp = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Toggle_Door_Closed.png");
            image[0] = temp;
            
            temp = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Toggle_Door_Open.png");
            image[1] = temp;
        }
        catch(Exception e){}
    }
    
    @Override
    public void collide(GameObject O) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics2D g) {
        if(close){
            g.drawImage(image[0], (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT, null);
        }
        else{
            g.drawImage(image[1], (int) super.x, (int) super.y, (int) super.WIDTH, (int) super.HEIGHT, null);
        }

        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }
}

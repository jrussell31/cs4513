package model.Moveable;

import controller.ImageFinder;
import controller.ObjectAnimator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import model.Direction;

public class Ball extends Monster {

    private ObjectAnimator ballMoves;
    private BufferedImage[] ballSprites;
    private int counter;

    public Ball(float x, float y, Direction D) {
        super(x, y);
        ballMoves = new ObjectAnimator();
        ballSprites = new BufferedImage[1];
        direction = D;
        moving = D;

        try {
            BufferedImage image = (BufferedImage) ImageFinder.getImage("ImagesFolder", "Pink_Ball.png");
            ballSprites[0] = image;
        } catch (Exception e) {
        }
    }

    public void turnAround() {
        direction = direction.getOppositeDirection();
        moving = direction;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(ballMoves.getImage(), (int) super.x, (int) super.y, 32, 32,
                null);
        //Draw Collision Box
        //g.setColor(Color.blue);
        //g.draw(this.getCollisionBox());
    }

    @Override
    public void update() {
        super.update();

        ballMoves.setFrames(ballSprites);

        if (isSliding()) {
            direction = moving;
            slide(moving);
        } else {
            if (counter == 1000) {
                counter = 0;
                switch (direction) {
                    case LEFT:
                        super.x -= MOVEMENT;
                        break;
                    case RIGHT:
                        super.x += MOVEMENT;
                        break;
                    case UP:
                        super.y -= MOVEMENT;
                        break;
                    case DOWN:
                        super.y += MOVEMENT;
                        break;
                }
                moving = direction;
            } else {
                counter += 100;
            }
        }
        ballMoves.update();
    }
}

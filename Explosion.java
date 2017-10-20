import greenfoot.*;

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    int timer;
    int currentFrame;
    
    public Explosion() {
        timer = 0;
        currentFrame = 1;
    }
    
    public void act() 
    {
        updatePos();
        
        timer++;
        
        if (timer >= 5) {
            // if past max frames, destroy it
            if (currentFrame >= 4) {
                destroySelf();
            } else {
                currentFrame++;
                setImage("explosion_" + currentFrame + ".png");
                timer = 0;
            }
        }
    }    
    
    public void destroySelf() {
        getWorld().removeObject(this);
    }
    
    public void updatePos() {
        Game world = (Game) getWorld();
        setLocation(world.player.getX(), world.player.getY());
    }
}

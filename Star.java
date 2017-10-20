import greenfoot.*;

/**
 * Write a description of class Star here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Star extends Actor
{
    public void act()  {
        moveLeft();
        checkBounds();
    }   
    
    // constant backwards motion
    public void moveLeft() {
        Game world = (Game) getWorld();
        setLocation(getX() - world.currentSpeed, getY());
    }
    
    public void checkBounds() {
        if (getX() <= 1) {
            Game world = (Game) getWorld();
            world.stars.remove(this);
            world.removeObject(this);
        }
    }
}

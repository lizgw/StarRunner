import greenfoot.*;

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Actor
{
    public void act()  {
        move();
        checkBounds();
    }
    
    // constant backwards motion
    public void move() {
        Game world = (Game) getWorld();
        setLocation(getX() - world.currentSpeed, getY() + world.currentSpeed);
    }
    
    public void checkBounds() {
        Game world = (Game) getWorld();
        if (getX() <= 1 || getY() >= world.getHeight() - 1) {
            world.removeObject(this);
        }
    }
}

import greenfoot.*;

/**
 * Write a description of class AsteroidBelt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AsteroidBelt extends Actor
{
    int asteroidTimer; // controls when to spawn asteroids
    int gapTimer; // controls when to create a gap
    boolean gap; // whether or not there's a gap currently
    
    public AsteroidBelt() {
        asteroidTimer = 20; // start it at 20 so an asteroid spawns immediately
        gapTimer = 0;
        gap = false;
        
        getImage().setTransparency(0);
    }
    
    public void act() 
    {
        moveLeft();
        gapControl();
        
        if (!gap) {
            createAsteroids();
        }
        checkBounds();
        
        asteroidTimer++;
        gapTimer++;
    }    
    
    // constant backwards motion
    public void moveLeft() {
        Game world = (Game) getWorld();
        setLocation(getX() - world.currentSpeed, getY());
    }
    
    // controls whether or not to create a gap
    public void gapControl() {
        if (gapTimer >= 75) {
            gap = true;
        }
        if (gapTimer >= 160) {
            gap = false;
            gapTimer = 0;
        }
    }
    
    public void createAsteroids() {
        Game world = (Game) getWorld();
        if (asteroidTimer >= world.currentSpeed + 17) {
            // offset the xPos so they're not all in a straight line
            int offsetX = Greenfoot.getRandomNumber(30);
            if (Greenfoot.getRandomNumber(2) < 1) {
                offsetX *= -1;
            }
            
            world.addObject(new Asteroid(), getX() + offsetX, getY());
            asteroidTimer = 0;
        }
    }
    
    public void checkBounds() {
        if (getX() <= 1) {
            getWorld().removeObject(this);
        }
    }
}

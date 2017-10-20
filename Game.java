import greenfoot.*;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    int difficulty; // a num from 0 to 2
    
    Player player;
    
    Text scoreText;
    int score;
    int scoreTimer;
    
    Healthbar hBar;
    
    ArrayList stars;
    Star nextStar;
    
    int playerMoveDist;
    int playerPosCounter;
    int playerSpeed;
    int extraDist;
    
    // prevent asteroid belts from spawning too close to each other
    int asteroidTimer;
    // controls how often stars are created
    int starTimer;
    
    int baseSpeed; // speed at the start of the game
    int currentSpeed; // speed now
    int speedTimer; // controls when the speed increases
    
    public Game(int diff)
    {    
        super(800, 450, 1);
        setPaintOrder(Text.class, Healthbar.class, Explosion.class, Player.class,
                      Star.class, Comet.class, Asteroid.class);
        
        difficulty = diff;
        
        baseSpeed = 2 + diff;
        currentSpeed = baseSpeed;
        
        playerMoveDist = 0;
        playerPosCounter = 0;
        playerSpeed = baseSpeed + 7;
        extraDist = 0;
        
        asteroidTimer = 0;
        starTimer = 0;
        
        score = 0;
        scoreText = new Text("Distance: " + score + " light years", 32, Color.WHITE);
        addObject(scoreText, getWidth() / 2, 20);
        
        
        stars = new ArrayList();
        generateStartStars();
        
        // create player & position at first star
        player = new Player();
        Star firstStar = (Star) stars.get(0);
        addObject(player, firstStar.getX(), firstStar.getY());
        
        nextStar = findNextStar();
        
        hBar = new Healthbar();
        addObject(hBar, getWidth() / 2, 50);
   }
    
    public void act() {
        generateStars();
        generateComets();
        generateAliens();
        if (asteroidTimer >= 90) {
            generateAsteroidBelts();
        }
        
        if (Greenfoot.isKeyDown("space")) {
            movePlayer(); // controls when the player moves to the next star
        }
        updatePlayerLocation(); // "animates" the player's position to the next star
        
        updateScore();
        
        // increase the speed every 5 seconds
        increaseSpeed();
        
        starTimer++;
        scoreTimer++;
        asteroidTimer++;
        speedTimer++;
    }
    
    public void movePlayer() {
        /* in the Game class rather than the player class for better access to
         * findNextStar() stuff w/o messing w/ static methods/vars */
        nextStar = findNextStar();
        
        if (nextStar != null) {
            // find distance to next star using distance formula
            int xDist = nextStar.getX() - player.getX();
            int yDist = nextStar.getY() - player.getY();
            int distance = (int) Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
            
            player.turnTowards(nextStar.getX(), nextStar.getY());
            
            // figure out how many frames the player needs to move for
            playerMoveDist = distance / playerSpeed;
            // move the remainder of pixels left over
            player.move(distance % playerSpeed);
            playerPosCounter = 0;
        }
    }
    
    // controls how the player moves from one star to the next
    public void updatePlayerLocation() {
        // if the player hasn't moved the full distance yet...
        if (playerPosCounter < playerMoveDist) {
            player.move(playerSpeed);
            playerPosCounter++;
        }
    }
    
    public void generateStars() {
        if (starTimer >= 70 - currentSpeed * 5) {
            Star s = new Star();
            int randHeight = Greenfoot.getRandomNumber(380) + 50;
            addObject(s, getWidth(), randHeight);
            s.setRotation(Greenfoot.getRandomNumber(360));
            stars.add(s); // add it to the arraylist
            starTimer = 0; // reset the timer
        }
    }
    
    // generate stars at the beginning so the screen isn't empty
    public void generateStartStars() {
        for (int i = 0; i < 3; i++) {
            Star s = new Star();
            int starY = Greenfoot.getRandomNumber(380) + 50;
            int starX = 180 * i + getWidth() / 2;
            addObject(s, starX, starY);
            stars.add(s); // add it to the arraylist
        }
    }
    
    public void generateComets() {
        if (Greenfoot.getRandomNumber(1000) < 3 + difficulty) {
            int[] coordinates = getRandomEdgePosition();
            addObject(new Comet(currentSpeed + 3), coordinates[0], coordinates[1]);
        }
    }
    
    public void generateAliens() {
        if (Greenfoot.getRandomNumber(1000) < 2 + difficulty) {
            int[] coordinates = getRandomEdgePosition();
            addObject(new Alien(currentSpeed + 1), coordinates[0], coordinates[1]);
        }
    }
    
    public void generateAsteroidBelts() {
        if (Greenfoot.getRandomNumber(1000) < 2 + difficulty) {
            addObject(new AsteroidBelt(), getWidth(), 0);
            asteroidTimer = 0; // reset the timer now that a belt has been created
        }
    }
    
    // returns an array w/ a random positon along the edge of the screen
    public int[] getRandomEdgePosition() {
        int randEdge = Greenfoot.getRandomNumber(3);
        int xPos;
        int yPos;
        
        switch (randEdge) {
            case 0:
                // left edge
                xPos = 0;
                yPos = Greenfoot.getRandomNumber(getHeight());
                break;
            case 1:
                // top edge
                xPos = Greenfoot.getRandomNumber(getWidth());
                yPos = 0;
                break;
            case 2:
                // right edge
                xPos = getWidth();
                yPos = Greenfoot.getRandomNumber(getHeight());
                break;
            case 3:
                // bottom edge
                xPos = Greenfoot.getRandomNumber(getWidth());
                yPos = getHeight();
                break;
            default:
                // top left corner
                xPos = 0;
                yPos = 0;
                break;
        }
        int[] coords = {xPos, yPos};
        return coords;
    }
    
    public void updateScore() {
        if (scoreTimer >= 30) {
            score++;
            scoreTimer = 0;
            scoreText.value = "Distance: " + score + " light years";
        }
    }
    
    // returns the star the player should move to next
    public Star findNextStar() {
        boolean foundStar = false;
        int index = 0;
        
        // loop through the stars arraylist
        while (!foundStar && index < stars.size()) {
            Star current = (Star) stars.get(index);
            // if the star is to the right of the player, return it
            if (current.getX() > player.getX()) {
                return current;
            }
            index++;
        }
        
        // if there is no next star yet, return null
        return null;
    }
    
    public void increaseSpeed() {
        if (speedTimer >= 60 * 5) {
            currentSpeed++;
            playerSpeed = currentSpeed + 7;
            speedTimer = 0;
        }
    }
    
    public void endGame(String deathMsg) {
        Greenfoot.delay(25); // give the player time to realize what happened
        removeObject(player);
        Greenfoot.setWorld(new GameOver(score, deathMsg, difficulty));
    }
}

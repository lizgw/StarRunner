import greenfoot.*;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    int action;
    
    /* 0 - start game & display difficulty options
     * 1 - instructions
     * 2 - about
     * 3 - back to main menu
     * 4 - quit to main menu
     * 5 - play again
     * 6 - start game with easy difficulty
     * 7 - start game with normal difficulty
     * 8 - start game with hard difficulty
     */
    
    public Button(int act) {
        action = act;
        
        switch (action) {
            case 0:
                setImage("btn_play.png");
                break;
            case 1:
                setImage("btn_instructions.png");
                break;
            case 2:
                setImage("btn_about.png");
                break;
            case 3:
                setImage("btn_back.png");
                break;
            case 4:
                setImage("btn_quit.png");
                break;
            case 5:
                setImage("btn_again.png");
                break;
            case 6:
                setImage("btn_easy.png");
                break;
            case 7:
                setImage("btn_normal.png");
                break;
            case 8:
                setImage("btn_hard.png");
                break;
        }
    }    
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            switch(action) {
                case 0:
                    getWorld().addObject(new Button(6), getWorld().getWidth() / 2 - 250, 175);
                    getWorld().addObject(new Button(7), getWorld().getWidth() / 2, 175);
                    getWorld().addObject(new Button(8), getWorld().getWidth() / 2 + 250, 175);
                    break;
                case 1:
                    Greenfoot.setWorld(new Instructions());
                    break;
                case 2:
                    Greenfoot.setWorld(new About());
                    break;
                case 3:
                case 4:
                    Greenfoot.setWorld(new MainMenu());
                    break;
                case 6:
                    Greenfoot.setWorld(new Game(0));
                    break;
                case 7:
                    Greenfoot.setWorld(new Game(1));
                    break;
                case 8:
                    Greenfoot.setWorld(new Game(2));
                    break;
                default:
                    System.out.println("Not a valid button choice!");
                    break;
            }
        }
    }    
}

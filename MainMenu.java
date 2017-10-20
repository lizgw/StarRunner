import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
    public MainMenu() {    
        super(800, 450, 1); 

        Button playBtn = new Button(0);
        addObject(playBtn, getWidth() / 2, 175);

        Button infoBtn = new Button(1);
        addObject(infoBtn, getWidth() / 2, 275);

        Button aboutBtn = new Button(2);
        addObject(aboutBtn, getWidth() / 2, 375);
    }
}

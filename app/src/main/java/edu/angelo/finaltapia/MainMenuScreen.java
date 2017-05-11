//Daniel Tapia - Final Project

package edu.angelo.finaltapia;

import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

/**
 * I changed the location of the inBounds area for the "Play" and "Highscores" text.
 * I assigned new png images to the background, logo, and mainMenu variables.
 * @author Daniel
 *
 */
public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);               
    }   

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                }
                // Adjust inBounds location in order to accommodate the new "Play" text
                if(inBounds(event, 100, 310, 120, 42)) {
                    game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                // Adjust for the new highscores text
                if(inBounds(event, 90, 310 + 55, 140, 42)) {
                    game.setScreen(new HighscoreScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 31, 135);
        g.drawPixmap(Assets.mainMenu, 64, 300);
       
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        else
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}


/**Daniel Tapia
 * 
 * In the GameScreen.java file I updated the code segments to add my "paused" audio file.
 * 
 * Changes made*******************************
 * The background color was changed to a light gray for the area above the game area.
 * The background color for the game area was changed to light green.
 * No "bitten" sound will play if a target is missed.
 * When quitting a game from the pause screen, the current score (if high enough) will
 * be added to the high scores list.
 * 
 * OTHER CHANGES:
 * I used Paint.NET to edit any new images that were added to this game.
 * In regards to my choice of my target image (Apple company logo) and the changes made to the menu screen, I'd
 * like to make it clear that I have no ill feelings toward Apple the company (I have an iphone5 and I love it).
 * The Android vs. Apple theme I chose for this game is not a reflection of my opinions of either Apple
 * or Android. I chose this theme strictly for the humor of it all :)
 */

package edu.angelo.finaltapia;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

import java.util.List;
import java.util.Random;

/**
 * I replaced the older audio files with new audio files which I made using CSound and Audacity.
 * The gameOver, paused, click, and bitten audio files were replaced. I also added a new gameWon audio.
 * 
 * GAME DESIGN NOTES (from The Art of Game Design book (AGD)):
 * One of the design elements from the AGD book that this game implements is the use of audio. 
 * I believe that the audio track of any game can certainly affect the tone and overall fun
 * factor of the experience. Although, I would have loved to make a complete audio track that 
 * would loop during the actual gameplay, I feel that having simple sounds for specific 
 * actions performed (pause, click) can improve the overall quality of the game.
 * 
 * Another element that I exercised well and frequently is playtesting. The author states that 
 * playtesting can be a difficult process (page 391), and although, I did not have a team of 
 * testers at my disposal, I did get quite a bit of practice at the game throughout the build phase.
 * 
 * Other than debugging, I believe that this part of the game design process can take the most time.
 *  
 * @author Daniel
 *
 */
public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver,
        GameWon,
        Level2,
        Level3, 
        NewBall
    }
    private static Random random = new Random();
    // pick random brick color from pixmap array
    private int color;
    GameState state = GameState.Ready;
    World world;
    
    int oldScore = 0;
    String score = "0";
    boolean firstTouch = true;
    boolean level1Play = false;
    boolean level2Play = false;
    boolean level3Play = false;
  
    //Pixmap array to hold each color brick
    public static Pixmap[] pixmapArray= new Pixmap[]{Assets.brickRed, Assets.brickBlue, Assets.brickGreen, Assets.brickOrange, Assets.brickPurple};
    
    public GameScreen(Game game) {
        super(game);
        world = new World();   
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);   
        if(state == GameState.GameWon)
            updateGameWon(touchEvents);  
        if(state == GameState.Level2)
            updateLevel2(touchEvents);  
        if(state == GameState.Level3)
            updateLevel3(touchEvents);  
        if(state == GameState.NewBall)
        	updateNewBall(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
            state = GameState.Running;
    }
    
    private void updateNewBall(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0)
        	state = GameState.Running;
        	world.ball = new Ball();
            world.newBall = false;
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 80 && event.y < 80) {
                    if(Settings.soundEnabled)
                        Assets.paused.play(1);
                    state = GameState.Paused;
                    return;
                }
            }
            
            // Variable for calculating distance from touch point to the paddle
            float touchPaddle = event.x - world.paddle.paddleX;
        	// Drag paddle along the touch drag path
            if(event.type == TouchEvent.TOUCH_DRAGGED)
            {		
            	// Calculate whether the touch location is on the paddle before dragging 
            	 if(touchPaddle <= 35 && touchPaddle >= -25 && event.y - world.paddle.paddleY >= 10) 
            	 { 
                   // Limit dragging to both edges of the game screen
            		if(event.x > 10 && event.x < world.WORLD_WIDTH - 30)
            		{
            			world.paddle.paddleX = event.x;
            		}
                 }
            }
            
            if(event.type == TouchEvent.TOUCH_DOWN) 
            {
            	
            		;
            }
        }
        
        world.update(deltaTime);
        if(world.gameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        
        
        // Game has been won
        if(world.gameWon)
        {
        	if(Settings.soundEnabled)
        		Assets.wingame.play(1);
        	state = GameState.GameWon;
        }
        // Level 2 is active
        if(world.level2)
        {
        	state = GameState.Level2; 	
        }
        // Level 3 is active
        if(world.level3)
        {
        	state = GameState.Level3;	
        }
        if(world.newBall)
        {
        	state = GameState.NewBall;	
        }
        /*
         *  eat sound will only be played if a target
         *  has been hit.
         */
        if(oldScore != world.score) {
            oldScore = world.score;
            score = "" + oldScore;
            if(Settings.soundEnabled && world.hit)
                Assets.eat.play(1);
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }                    
                    // Choose quit
                    if(event.y > 148 && event.y < 196) {
                    	// Add to high scores and save to file
                    	 Settings.addScore(world.score);
                         Settings.save(game.getFileIO());
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game)); 
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }
    // Game won
    private void updateGameWon(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }
    
    // Level 2
    private void updateLevel2(List<TouchEvent> touchEvents) {
    	if(touchEvents.size() > 0)
    	{
            state = GameState.Running;
    	}		
    }
    // Level3
    private void updateLevel3(List<TouchEvent> touchEvents) {
    	if(touchEvents.size() > 0)
    	{
            state = GameState.Running;
    	}
    }
    
    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        if(state == GameState.GameWon)
            drawGameWonUI();
        if(state == GameState.Level2)
            drawReady2UI();
        if(state == GameState.Level3)
            drawReady3UI();
        if(state == GameState.NewBall)
            drawNewBallUI();
        
        drawText(g, score, g.getWidth() - score.length() * 20, 0);
        
        int space = 0;
        // Show the number of balls remaining in each game
        for(int i = 0; i < world.ballCount; i++)
        {
        	space = space + 15;
        	g.drawPixmap(Assets.ball, g.getWidth() - space, 35);
        }
    }

    private void drawWorld(World world) {
        Graphics g = game.getGraphics();
        g.clear(Color.rgb(0, 128, 155));
        
        // Draw game area
        g.drawRect(0, 55, World.WORLD_WIDTH, World.WORLD_HEIGHT, Color.rgb(0, 0, 0));
        // Draw the paddle
        g.drawPixmap(Assets.paddle, world.paddle.paddleX - 15, world.paddle.paddleY );
        
        if(level1Play)
        {
        	
        	for(int i = 0; i < world.brickSet.bricks.size(); i++)
        	{
        	// pixmapArray - [0]red, [1]blue, [2]green, [3]orange, [4]purple
        		g.drawPixmap(pixmapArray[color], world.brickSet.bricks.get(i).locationX, world.brickSet.bricks.get(i).locationY);
        	}
        	// Draw the ball
        	g.drawPixmap(Assets.ball, world.ball.ballX, world.ball.ballY);
        }
        else if(level2Play)
        {
        	
        	for(int i = 0; i < world.brickSet.bricks.size(); i++)
        	{
        	// pixmapArray - [0]red, [1]blue, [2]green, [3]orange, [4]purple
        		g.drawPixmap(pixmapArray[color], world.brickSet.bricks.get(i).locationX, world.brickSet.bricks.get(i).locationY);
        	}
        	// Draw the ball
        	g.drawPixmap(Assets.ball, world.ball.ballX, world.ball.ballY);
        }
        
        else if(level3Play)
        {
        	
        	for(int i = 0; i < world.brickSet.bricks.size(); i++)
        	{
        	// pixmapArray - [0]red, [1]blue, [2]green, [3]orange, [4]purple
        		g.drawPixmap(pixmapArray[color], world.brickSet.bricks.get(i).locationX, world.brickSet.bricks.get(i).locationY);
        	}
        	// Draw the ball
        	g.drawPixmap(Assets.ball, world.ball.ballX, world.ball.ballY);
        }
        
    }
    
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.level1, 47, 200);
        color = random.nextInt((4 - 0) + 1) + (0);
        world.level1 = false;
        level1Play = true;
    }
    
    // NewBall
    private void drawNewBallUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 47, 200);
    }
    
    private void drawReady2UI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.level2, 47, 200);
        color = random.nextInt((4 - 0) + 1) + (0);
        world.brickSet = new BrickSet(5,6);
        world.ball = new Ball();
        world.level2 = false;  
        level1Play = false;
        level2Play = true;
    }
    
    private void drawReady3UI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.level3, 47, 200);
        color = random.nextInt((4 - 0) + 1) + (0);
        world.brickSet = new BrickSet(20);
        world.brickSet.moveBricks();
        world.ball = new Ball();
        world.level3 = false;  
        level2Play = false;
        level3Play = true;
    }
    
    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        // Draw pause button
        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);   
    }
    
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.pause, 80, 100);
        
    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        
    }
    
    // Draw new images for gameWon 
    // Have a next level
    private void drawGameWonUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameWon, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        
    }
    
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;
        
        if(world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void dispose() {
        
    }
}


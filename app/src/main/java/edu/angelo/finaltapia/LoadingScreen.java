package edu.angelo.finaltapia;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("menu.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.level1 = g.newPixmap("level1.png", PixmapFormat.ARGB4444);
        Assets.level2 = g.newPixmap("level2.png", PixmapFormat.ARGB4444);
        Assets.level3 = g.newPixmap("level3.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("newball.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.gameWon = g.newPixmap("gamewon.png", PixmapFormat.ARGB4444);
        Assets.brickRed = g.newPixmap("brickRed.png", PixmapFormat.ARGB4444);
        Assets.brickBlue = g.newPixmap("brickBlue.png", PixmapFormat.ARGB4444);
        Assets.brickGreen = g.newPixmap("brickGreen.png", PixmapFormat.ARGB4444);
        Assets.brickOrange = g.newPixmap("brickOrange.png", PixmapFormat.ARGB4444);
        Assets.brickPurple = g.newPixmap("brickPurple.png", PixmapFormat.ARGB4444);
        Assets.paddle = g.newPixmap("paddle.png", PixmapFormat.ARGB4444);
        Assets.ball = g.newPixmap("ball.png", PixmapFormat.ARGB4444);
        
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("gameover.ogg");
        Assets.paused = game.getAudio().newSound("paused.ogg");
        Assets.wingame = game.getAudio().newSound("wingame.ogg");
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }
    
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}

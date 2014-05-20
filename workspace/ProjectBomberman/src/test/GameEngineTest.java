package system;

import org.junit.*;

import javax.swing.*;
import java.io.*;

/**
 * Test file for use-cases specified in the SRS
 */
public class GameEngineTest {
    @Test
    public void testPauseGame() throws Exception {
        GameEngine.pauseGame();

        Assert.assertTrue(GameEngine.isPaused);
    }

    @Test
    public void testResumeGame() throws Exception {
        GameEngine.pauseGame();

        Assert.assertTrue(GameEngine.isPaused);

        GameEngine.resumeGame();

        Assert.assertFalse(GameEngine.isPaused);
    }

    @Test
    public void testSaveGame() throws Exception {
        GameStatus gameStatus = new GameStatus();
        gameStatus.addPlayer(new Player(32,32));

        String savedPath = GameEngine.saveGame(gameStatus);

        File testFile = new File(savedPath);
        boolean fileExists = testFile.exists();

        Assert.assertTrue(fileExists);
    }

    @Test
    public void testStartOnePlayerGame() throws Exception {
        GameStatus gs = new GameStatus();
        GamePhysics gp = new GamePhysics(gs);
        JFrame frame = new JFrame();
        GameGraphics gg = new GameGraphics(gs, frame);

        GameEngine.graphicsThread = new Thread(gg);
        GameEngine.physicsThread = new Thread(gp);

        GameEngine.physicsThread.run();
        GameEngine.graphicsThread.run();

        GameEngine.gs = gs;

        GameEngine.startOnePlayerGame();
        Assert.assertTrue(gs.isOnePlayer());
        Assert.assertTrue(gs.getPlayers().size()==1);
        Assert.assertTrue(gs.getBombs().size()==0);
        Assert.assertTrue(gs.getHardBlocks().size()!=0);
        Assert.assertTrue(gs.getEnemies().size()!=0);
        Assert.assertTrue(gs.getSoftBlocks().size()!=0);
        Assert.assertFalse(gs.isGameOver());
        Assert.assertTrue(GameEngine.physicsThread.isAlive());
        Assert.assertTrue(GameEngine.graphicsThread.isAlive());
    }

    @Test
    public void testStartTwoPlayerGame() throws Exception {
        GameStatus gs = new GameStatus();
        GamePhysics gp = new GamePhysics(gs);
        JFrame frame = new JFrame();
        GameGraphics gg = new GameGraphics(gs, frame);

        GameEngine.graphicsThread = new Thread(gg);
        GameEngine.physicsThread = new Thread(gp);

        GameEngine.physicsThread.run();
        GameEngine.graphicsThread.run();

        GameEngine.gs = gs;

        GameEngine.startTwoPlayerGame();
        Assert.assertTrue(gs.getPlayers().size()==2);
        Assert.assertTrue(gs.getPlayers().get(0).isActive());
        Assert.assertTrue(gs.getBombs().size()==0);
        Assert.assertTrue(gs.getHardBlocks().size()!=0);
        Assert.assertTrue(gs.getEnemies().size()!=0);
        Assert.assertTrue(gs.getSoftBlocks().size()!=0);
        Assert.assertFalse(gs.isGameOver());
        Assert.assertTrue(GameEngine.physicsThread.isAlive());
        Assert.assertTrue(GameEngine.graphicsThread.isAlive());
    }
}

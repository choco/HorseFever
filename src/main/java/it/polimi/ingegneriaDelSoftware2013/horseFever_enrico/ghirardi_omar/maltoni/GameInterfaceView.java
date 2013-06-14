package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 14/06/13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class GameInterfaceView extends JFrame {
    private GameMenuView gameMenu;
    private GameLobbyView gameLobby;
    private GamePanelView gamePanel;
    private static final String imageDir = "rsc/";

    public GameInterfaceView() {
        super("Horse Fever v0.5");

        gameMenu = new GameMenuView(this);
        this.setContentPane(gameMenu);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        //set the frame at the center of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    void setGamePanelAsPane() {
        gamePanel = new GamePanelView(this);
        this.setContentPane(gamePanel);
        this.setSize(1400, 980);

        //set the frame at the center of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    String getImagedir() {
        return imageDir;
    }
}

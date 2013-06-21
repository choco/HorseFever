package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 14/06/13
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class GameMenuView extends JPanel {
    private JButton offlineGame;
    private JButton onlineGame;
    private GameInterfaceView viewRef;

    /**
     * Constructor of a game menu view object which is the first screen of the application
     *
     * @param view reference to the main frame of the gui
     */
    public GameMenuView(GameInterfaceView view) {

        viewRef = view;

        setLayout(new GridLayout(2, 1));

        offlineGame = new JButton("Offline Game");
        offlineGame.setToolTipText("Start a new exciting game on this PC with your friends!");
        add(offlineGame);

        onlineGame = new JButton("Online Game");
        onlineGame.setToolTipText("Create or join an online game! (Yet to be implemented)");
        add(onlineGame);

        MenuHandler handler = new MenuHandler();
        offlineGame.addActionListener(handler);
        onlineGame.addActionListener(handler);

        Dimension size = new Dimension(200, 300);
        setPreferredSize(size);
    }

    /**
     * Action listener which reacts to player actions on the gui
     */

    private class MenuHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == offlineGame) {
                viewRef.setGameLobbyAsMainPanel();

            } else if (event.getSource() == onlineGame) {
                System.out.println("comincia online");
            }

        }
    }
}



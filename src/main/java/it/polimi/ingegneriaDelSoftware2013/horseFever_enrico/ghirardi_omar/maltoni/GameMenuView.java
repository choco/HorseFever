package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

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

    //TODO: lanciare da qui menu personalizza partita da cui inizializzare i giocatori;
    GameMenuView(GameInterfaceView view) {

        viewRef = view;
        setLayout(new GridLayout(2, 1));

        offlineGame = new JButton("Offline Game");
        offlineGame.setToolTipText("Start a new exciting game on this PC with your friends!");
        add(offlineGame);

        onlineGame = new JButton("Online Game");
        onlineGame.setToolTipText("Create or join an online game! (Yet to be implemented)");
        add(onlineGame);

        LobbyHandler handler = new LobbyHandler();
        offlineGame.addActionListener(handler);
        onlineGame.addActionListener(handler);
    }

    private class LobbyHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == offlineGame) {

                viewRef.setGamePanelAsPane();

            } else if (event.getSource() == onlineGame) {
                System.out.println("comincia online");
            }

        }
    }
}



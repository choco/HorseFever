package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 14/06/13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class GameInterfaceView extends JFrame implements GameInterface {
    private MatchController matchController;
    private Thread gameFlow;
    private GameMenuView gameMenu;
    private GameLobbyView gameLobby;
    private GamePanelView gamePanel;
    private static final String imageDir = "rsc/";

    public GameInterfaceView(MatchController matchController) {
        super("Horse Fever");
        this.matchController = matchController;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameMenu = new GameMenuView(this);
        gameLobby = new GameLobbyView(this);
        gamePanel = new GamePanelView(this);

        this.setContentPane(gameMenu);
        this.setResizable(false);
        this.setVisible(true);

        pack();
        centerWindow();
    }

    void setGamePanelAsMainPanel() {
        this.setContentPane(gamePanel);

        pack();
        centerWindow();
    }

    void startMatchWithPlayers(ArrayList<String> playersNicknames) {
        matchController.setPlayers(playersNicknames);
        setGamePanelAsMainPanel();

        gameFlow = new Thread(new Runnable() {
            @Override
            public void run() {
                matchController.startMatch();
            }
        });

        gameFlow.start();
    }

    void pauseGameFlow() {
        try {
            synchronized (this) {
                wait();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void resumeGameFlow() {
        synchronized (this) {
            notifyAll();
        }
    }

    void setGameLobbyAsMainPanel() {

        this.remove(gameMenu);
        this.setContentPane(gameLobby);

        pack();
        centerWindow();
    }

    private void centerWindow() {
        //set the frame at the center of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    String getImagedir() {
        return imageDir;
    }

    public void updateBetMarkPool(Map<StableColor, Integer> betMarkPool) {
        gamePanel.updateBetMarks(betMarkPool);
    }

    public void updatePlayersInfo(ArrayList<Player> players) {
        gamePanel.setUpPlayersPanels(players);
    }

    public boolean userWantsToBet() {
        int result = JOptionPane.showConfirmDialog(this, "Second Bet", "Do you want to make a second bet?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println("VVediamo se qui");
            return true;
        }
        System.out.println("Oppure qui");

        return false;
    }

    public Bet getPlayerBet(ArrayList<Stable> stables) {
        return gamePanel.getPlayerBet(stables);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void betWasRegisteredCorrectly() {
        JOptionPane.showConfirmDialog(this, "FATTAAA!");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void betRegistrationError(String s) {
        gamePanel.showBetRegistrationError(s);

    }

    public void updateUIForPhase(MatchPhase matchPhase) {
        switch (matchPhase) {

            case START_GAME:
                break;
            case FIRST_BET_PHASE:
                gamePanel.hideAllRightPanels();
                gamePanel.showBetPanels();
                break;
            case SECOND_BET_PHASE:
                gamePanel.hideAllRightPanels();
                gamePanel.showBetPanels();
                break;
            case RIG_PHASE:
                gamePanel.hideAllRightPanels();
                break;
            case RACE_PHASE:
                break;
            case END_GAME:
                break;
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setCurrentPlayer(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public ActionCard getActionCardToPlay(ArrayList<ActionCard> cards) {

        return cards.get(0);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Horse getHorseToPlayActionCardOn(ArrayList<Horse> horses) {
        System.out.println("Cavallo: " + horses.get(0).getOwnerStable().getColor());
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = r.readLine();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return horses.get(0);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void playerHasLostTheGame(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}

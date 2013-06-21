package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.views;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.MatchController;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.interfaces.GameInterface;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.*;

import javax.swing.*;
import java.awt.*;
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

    /**
     * Constructor of game interface view object which is the main frame of the gui
     *
     * @param matchController link to the controller
     */

    public GameInterfaceView(MatchController matchController) {
        super("Horse Fever");
        this.matchController = matchController;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameMenu = new GameMenuView(this);
        gameLobby = new GameLobbyView(this);
        gamePanel = new GamePanelView(this, matchController.getRaceManager());

        this.setContentPane(gameMenu);
        this.setResizable(false);
        this.setVisible(true);

        pack();
        centerWindow();
    }

    /**
     * Sets the game panel as the main panel of the frame (this) by removing the older one
     */

    void setGamePanelAsMainPanel() {
        this.setContentPane(gamePanel);

        pack();
        centerWindow();
    }

    /**
     * Assigns the id tags of the players to the matchcontroller which creates the players, sets game panel as the current screen of the gui and starts the gameflow thread
     *
     * @param playersNicknames array list of tags representing each player
     */

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

    /**
     * Temporarily stops the thread
     */

    void pauseGameFlow() {
        try {
            synchronized (this) {
                wait();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Resumes the thread
     */

    void resumeGameFlow() {
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * Sets the game lobby panel as the main panel of the frame (this) by removing the older one
     */

    void setGameLobbyAsMainPanel() {

        this.remove(gameMenu);
        this.setContentPane(gameLobby);

        pack();
        centerWindow();
    }

    /**
     * Puts the frame at the center of the screen
     */

    private void centerWindow() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    String getImagedir() {
        return imageDir;
    }

    /**
     * Updates the bet mark pool after every choice of the players
     *
     * @param betMarkPool hash map representing the bet mark pool to update
     */

    public void updateBetMarkPool(Map<StableColor, Integer> betMarkPool) {
        gamePanel.updateBetMarks(betMarkPool);
    }

    /**
     * Updates players info after every changing
     *
     * @param players array list representing the players taking part in the game
     */

    public void updatePlayersInfo(ArrayList<Player> players) {
        gamePanel.setUpPlayersPanels(players);
    }

    /**
     * Checks if the current user wants to make the second bet
     *
     * @return a boolean value representing the choice of the current player
     */

    public boolean userWantsToBet(Player player) {
        int result = JOptionPane.showConfirmDialog(this, "It's " + player.getIdTag() + "'s turn.\nDo you want to make a second bet?", "Second bet", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            gamePanel.updateGameLog("\n" + player.getIdTag() + "wants to make a second bet. Keep an eye on this smart fellow!");
            return true;
        }
        return false;
    }

    /**
     * Get the bet from the player through the game panel gui
     *
     * @param stables array list of stable on which the player will bet
     * @return the bet made by the player
     */

    public Bet getPlayerBet(ArrayList<Stable> stables) {
        return gamePanel.getPlayerBet(stables);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Informs the player if his bet has correctly been registered
     */

    public void betWasRegisteredCorrectly() {
        JOptionPane.showMessageDialog(this, "Your bet has been taken!", "Correct Bet", JOptionPane.INFORMATION_MESSAGE);
        gamePanel.updateGameLog("Bet registered!");
        gamePanel.clearBetFields();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Informs the player if his bet isn't valid
     *
     * @param s the error message to show
     */

    public void betRegistrationError(String s) {
        gamePanel.showBetRegistrationError(s);

    }

    /**
     * Updates the right end of the gui depending on the games phase
     *
     * @param matchPhase the phase of the match
     */

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
                gamePanel.showRigPanel();
                gamePanel.showActionDescriptionTextArea();
                break;
            case RACE_PHASE:
                gamePanel.hideAllRightPanels();
                gamePanel.showRacePanel();
                gamePanel.showActionDescriptionTextArea();
                break;
            case END_GAME:
                break;
        }
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void setCurrentPlayer(Player player) {
        JOptionPane.showMessageDialog(this, "It's " + player.getIdTag() + "'s turn! \nLeave the computer and don't peek!\n Or do it...you're a felon after all", "It's " + player.getIdTag() + "'s turn", JOptionPane.INFORMATION_MESSAGE);
        gamePanel.updateGameLog("It's " + player.getIdTag() + "'s turn!");
    }

    public void playerHasLostTheGame(Player player) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void playerHasWonTheGame(Player winner) {
        gamePanel.updateGameLog(winner.getIdTag() + "has WON!!!\n In the end he has" + winner.getVictoryPoints()
                + " Victory Points and" + winner.getMoney() + " golds");
        JOptionPane.showMessageDialog(this, winner.getIdTag() + "has WON!!!\n In the end he has" + winner.getVictoryPoints()
                + " Victory Points and" + winner.getMoney() + " golds", "Congratulations " + winner.getIdTag(), JOptionPane.INFORMATION_MESSAGE);
    }

    public void userShouldStartRaceTurn() {
        pauseGameFlow();
    }

    public void updateRacePhase(RacePhase racePhase) {
        gamePanel.updateRacePhase(racePhase);
    }

    public void userShouldThrowSprintDices() {
        pauseGameFlow();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void showSprintingHorses(ArrayList<StableColor> sprintingColors) {
        gamePanel.showSprintingHorses(sprintingColors);
    }

    public void updateCurrentMovementCard(MovementCard movementCard) {
        gamePanel.updateCurrentMovementCard(movementCard);
    }

    public void updateStableQuotations(ArrayList<Stable> stables) {
        gamePanel.updateStableQuotations(stables);
    }

    public ArrayList getActionCardToPlayOnHorse(ArrayList<Horse> horses, ArrayList<ActionCard> actionCardPile) {
        return gamePanel.getActionCardToPlayOnHorse(horses, actionCardPile);
    }

    public void updateRaceStandings(Map<Stable, Integer> standing) {
        gamePanel.updateRaceStanding(standing);
    }

}

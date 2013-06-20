package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 14/06/13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */

public class GamePanelView extends JPanel {
    MouseClickHandler mouseListener;
    private JPanel leftPanel;
    private JPanel midPanel;
    private StableQuotationsPanel quotationsPanel;
    private JPanel rightPanel;
    private Box playersBox;
    ButtonHandler sampleHandler;

    private JLabel playerCard;
    private JLabel stableCard;
    private JTextArea gameLog;
    //private JButton throwDice;
    //private JButton action1;
    //private JButton nextTurn;
    private GameInterfaceView viewRef;


    private JPanel racePanel;
    private JButton prossimoTurnoDiCorsaButton;
    private JButton faiSprintareICavalliButton;
    private JPanel movementCardPanel;
    private JLabel cardLabel;
    private JPanel firstSpriningHorse;
    private JPanel secondSprintingHorse;


    private JPanel betMarkPoolPanel;
    private JTextArea[] betMarkValue;
    private JPanel betPanel;
    private ButtonGroup horseColorBtnGroup;
    private ButtonGroup betTypeBtnGroup;
    private JTextField betAmountField;
    private JLabel betError;
    private JButton registraScommessaBtn;
    private JRadioButton neroRadioButton;
    private JRadioButton bluRadioButton;
    private JRadioButton verdeRadioButton;
    private JRadioButton piazzataRadioButton;
    private JRadioButton vincenteRadioButton;
    private JRadioButton rossoRadioButton;
    private JRadioButton gialloRadioButton;
    private JRadioButton biancoRadioButton;

    /**
     * Constructor of a game panel view object
     *
     * @param view reference to the main frame of the gui
     */
    GamePanelView(GameInterfaceView view, RaceManager manager) {


        viewRef = view;
        setLayout(new BorderLayout());

        mouseListener = new MouseClickHandler();
        sampleHandler = new ButtonHandler();


        //left part of the gui
        leftPanel = new JPanel(new BorderLayout());

        //players panel
        playersBox = Box.createVerticalBox();

        leftPanel.add(playersBox, BorderLayout.NORTH);
        //players added

        //gameLog panel
        gameLog = new JTextArea(0, 40);
        gameLog.setBorder(new TitledBorder("Game Log"));
        gameLog.setLineWrap(true);
        gameLog.setEditable(false);
        leftPanel.add(gameLog, BorderLayout.CENTER);
        leftPanel.add(new JScrollPane(gameLog), BorderLayout.CENTER);
        initializeGameLog();
        //gameLog added

        //aggiunge leftPanel al frame
        add(leftPanel, BorderLayout.WEST);

        //creates board panel
        midPanel = new BoardPanel(viewRef.getImagedir() + "other/board.jpg", manager);
        quotationsPanel = new StableQuotationsPanel(viewRef.getImagedir() + "other/stableQuotationsBoard.png");
        quotationsPanel.setPreferredSize(new Dimension(420, 200));
        midPanel.add(quotationsPanel);
        //adds midPanel to frame
        add(midPanel, BorderLayout.CENTER);

        //creates rightPanel
        rightPanel = new JPanel();
        Box rightBox = Box.createVerticalBox(); //ordina i componenti del panel;
        rightBox.setPreferredSize(new Dimension(500, 600));

        //bet mark pool panel
        betMarkValue = new JTextArea[6];
        buildBetMarkPoolPanel();
        betMarkPoolPanel.setVisible(false);
        rightBox.add(betMarkPoolPanel);
        //bet marks added

        buildBetPanel();
        betPanel.setVisible(false);
        rightBox.add(betPanel);


        buildRacePanel();
        racePanel.setVisible(false);
        rightBox.add(racePanel);
        /*
        //pulsanti azione
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBorder((new TitledBorder("Action Buttons")));
        //per ora ci agigunge 3 pulsanti
        throwDice = new JButton("Throw Dice");
        actionPanel.add(throwDice);
        action1 = new JButton("Action 1");
        actionPanel.add(action1);
        nextTurn = new JButton("Next Turn");
        actionPanel.add(nextTurn);

        rightBox.add(actionPanel);

        //aggiunti i pulsanti azione    */

        //textArea actionCard description
        JTextArea actionDescription = new JTextArea(15, 32);
        actionDescription.setLineWrap(true);
        actionDescription.setBorder(new TitledBorder("Action Card Description"));
        rightBox.add(actionDescription);
        //added last element to default rightBox

        //adds rightBox to rightPanel
        rightPanel.add(rightBox);
        //adds last panel
        add(rightPanel, BorderLayout.EAST);

        //creo e aggiungi handler
        //throwDice.addActionListener(sampleHandler);
        //action1.addActionListener(sampleHandler);
        //nextTurn.addActionListener(sampleHandler);

        Dimension size = new Dimension(1600, 980);
        setPreferredSize(size);

    }

    private void buildRacePanel() {
        racePanel = new JPanel();
        racePanel.setLayout(new GridBagLayout());
        racePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Race"));

        movementCardPanel = new JPanel();
        movementCardPanel.setLayout(new GridBagLayout());
        cardLabel = new JLabel(new ImageIcon("rsc/cards/movementcards/back.png"));
        movementCardPanel.add(cardLabel);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 2.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        racePanel.add(movementCardPanel, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        racePanel.add(panel2, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        panel2.add(panel3, gbc);
        faiSprintareICavalliButton = new JButton();
        faiSprintareICavalliButton.setText("Fai sprintare i cavalli");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(faiSprintareICavalliButton, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(panel4, gbc);
        firstSpriningHorse = new JPanel();
        firstSpriningHorse.setLayout(new BorderLayout(0, 0));
        firstSpriningHorse.setPreferredSize(new Dimension(50, 50));
        firstSpriningHorse.setBackground(Color.white);
        firstSpriningHorse.setBorder(BorderFactory.createLoweredBevelBorder());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(firstSpriningHorse, gbc);
        secondSprintingHorse = new JPanel();
        secondSprintingHorse.setLayout(new BorderLayout(0, 0));
        secondSprintingHorse.setPreferredSize(new Dimension(50, 50));
        secondSprintingHorse.setBackground(Color.white);
        secondSprintingHorse.setBorder(BorderFactory.createLoweredBevelBorder());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel4.add(secondSprintingHorse, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer2, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        racePanel.add(panel5, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel5.add(spacer4, gbc);
        prossimoTurnoDiCorsaButton = new JButton();
        prossimoTurnoDiCorsaButton.setText("Prossimo turno di corsa");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel5.add(prossimoTurnoDiCorsaButton, gbc);

        faiSprintareICavalliButton.addActionListener(sampleHandler);
        prossimoTurnoDiCorsaButton.addActionListener(sampleHandler);

    }

    /**
     * Resizes the images received to the following dimension: width=w and height=i
     *
     * @param srcImg image to resize
     * @param w      final width
     * @param h      final height
     * @return resized image
     */

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Initializes the game log with a default line
     */

    public void initializeGameLog() {
        this.gameLog.append("Let the game begin!");
    }

    /**
     * Updates the gameLog
     */

    public void updateGameLog(String update) {
        gameLog.append("\n" + update);
    }

    /**
     * Get the character cards path
     *
     * @return a string representing the path to the character cards directory
     */

    String getCharDir() {
        return viewRef.getImagedir() + "cards/charactercards/";

    }

    /**
     * Get the stable cards path
     *
     * @return a string representing the path to the stable cards directory
     */
    String getStableDir() {
        return viewRef.getImagedir() + "cards/stablecards/";

    }

    /**
     * Get the action cards path
     *
     * @return a string representing the path to the stable cards directory
     */
    String getActionDir() {
        return viewRef.getImagedir() + "cards/actioncards/";

    }

    /**
     * Shows a message of error if the bet is invalid
     *
     * @param s the actual message shown
     */

    public void showBetRegistrationError(String s) {
        betError.setText(s);
        betError.setForeground(Color.RED);
    }

    /**
     * Hides the right panels
     */

    public void hideAllRightPanels() {
        hideRacePanel();
        hideBetPanels();
    }

    public void clearBetFields() {
        betAmountField.setText("");
        betError.setText("");
        neroRadioButton.setSelected(true);
        piazzataRadioButton.setSelected(true);
    }

    public void updateRacePhase(RacePhase racePhase) {

        switch (racePhase) {

            case START:
                prossimoTurnoDiCorsaButton.setText("Inizia la corsa");
                prossimoTurnoDiCorsaButton.setEnabled(true);
                faiSprintareICavalliButton.setEnabled(false);
                revalidate();
                repaint();
                break;
            case MIDDLE:
                prossimoTurnoDiCorsaButton.setText("Inizia prossimo turno di corsa");
                prossimoTurnoDiCorsaButton.setEnabled(true);
                faiSprintareICavalliButton.setEnabled(false);
                break;
            case SPRINT:
                prossimoTurnoDiCorsaButton.setEnabled(false);
                faiSprintareICavalliButton.setEnabled(true);
                break;
            case FINISH:
                prossimoTurnoDiCorsaButton.setEnabled(false);
                faiSprintareICavalliButton.setEnabled(false);
                break;
        }
    }

    public void showSprintingHorses(ArrayList<StableColor> sprintingColors) {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (StableColor color : sprintingColors) {
            switch (color) {

                case BLACK:
                    colors.add(Color.BLACK);
                    break;
                case BLUE:
                    colors.add(Color.BLUE);
                    break;
                case GREEN:
                    colors.add(Color.GREEN);
                    break;
                case RED:
                    colors.add(Color.RED);
                    break;
                case YELLOW:
                    colors.add(Color.YELLOW);
                    break;
                case WHITE:
                    colors.add(Color.WHITE);
                    break;
            }
        }

        if (colors.size() == 1) {
            firstSpriningHorse.setBackground(colors.get(0));
            secondSprintingHorse.setBackground(colors.get(0));
        } else {
            firstSpriningHorse.setBackground(colors.get(0));
            secondSprintingHorse.setBackground(colors.get(1));
        }
        //To change body of created methods use File | Settings | File Templates.
    }

    public void updateCurrentMovementCard(MovementCard movementCard) {
        cardLabel.setIcon(new ImageIcon("rsc/cards/movementcards/" + movementCard.getImagePath()));
        revalidate();
        repaint();
    }

    public void updateStableQuotations(ArrayList<Stable> stables) {
        quotationsPanel.updateStableQuotations(stables);
    }

    /**
     * Action listener of the register bet button
     */

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == registraScommessaBtn || event.getSource() == betAmountField) {
                registerBet();
            } else if (event.getSource() == faiSprintareICavalliButton || event.getSource() == prossimoTurnoDiCorsaButton) {
                viewRef.resumeGameFlow();
            }

        }
    }

    /**
     * Shows bet phase panels to the right of the gui
     */

    public void showBetPanels() {
        betMarkPoolPanel.setVisible(true);
        betPanel.setVisible(true);
    }

    public void showRacePanel() {
        racePanel.setVisible(true);
    }

    public void hideRacePanel() {
        racePanel.setVisible(false);
    }

    /**
     * Hides bet phase panels
     */

    public void hideBetPanels() {
        betMarkPoolPanel.setVisible(false);
        betPanel.setVisible(false);
    }

    /**
     * Mouse listener which shows the character and stable card of a player when clicked
     */

    private class MouseClickHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent event) {
            if (event.getSource() == stableCard) {
                JOptionPane.showMessageDialog(null, null, "Stable Card",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon(getStableDir() + "stablecard55.jpg"));
            } else if (event.getSource() == playerCard) {
                JOptionPane.showMessageDialog(null, null, "Character Card",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon(getCharDir() + "charactercard71.jpg"));
            }

        }
    }

    /**
     * Builds the player panels
     *
     * @param players array list of players, every player has his own player panel
     */

    void setUpPlayersPanels(ArrayList<Player> players) {
        playersBox.removeAll();
        for (Player player : players) {
            playersBox.add(createInfoPanelForPlayer(player));
        }
        repaint();
    }

    /*public boolean wantsToPlayAgain() {

        viewRef.pauseGameFlow();
        /*while(!fine){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        final boolean[] wait = new boolean[1];
        wait[0] = false;
        do {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        wait[0] = getFine();
                    }
                });
        } while (!wait[0]);
        return true;
    }  */

    /**
     * Registers a bet
     */

    void registerBet() {
        viewRef.resumeGameFlow();
    }

    /**
     * Translates the choices of a player during the bet phase in a bet which can be read by the controller
     *
     * @param stables array list of stables on which the player bets
     * @return the bet made
     */

    Bet getPlayerBet(ArrayList<Stable> stables) {
        boolean correctInputFalse = false;
        int amount = 0;

        while (!correctInputFalse) {
            betAmountField.requestFocus();
            viewRef.pauseGameFlow();
            try {
                amount = Integer.parseInt(betAmountField.getText());
                correctInputFalse = true;
            } catch (NumberFormatException e) {
                showBetRegistrationError("Insert a valid amount!");
            }
        }
        StableColor color = null;
        if (neroRadioButton.isSelected()) {
            color = StableColor.BLACK;
        } else if (bluRadioButton.isSelected()) {
            color = StableColor.BLUE;
        } else if (verdeRadioButton.isSelected()) {
            color = StableColor.GREEN;
        } else if (rossoRadioButton.isSelected()) {
            color = StableColor.RED;
        } else if (gialloRadioButton.isSelected()) {
            color = StableColor.YELLOW;
        } else if (biancoRadioButton.isSelected()) {
            color = StableColor.WHITE;
        }
        Stable bettingStable = null;
        for (Stable stable : stables) {
            if (stable.getColor() == color)
                bettingStable = stable;
        }

        BetType type = null;
        if (piazzataRadioButton.isSelected()) {
            type = BetType.PLACED;
        } else if (vincenteRadioButton.isSelected()) {
            type = BetType.WINNING;
        }

        return new Bet(null, amount, bettingStable, type);
    }

    /**
     * Builds bet panel on the right of the gui
     */

    void buildBetPanel() {

        betPanel = new JPanel();
        betPanel.setLayout(new GridBagLayout());
        betPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7), null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Make a bet"));

        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        betPanel.add(panel1, gbc);

        final JLabel label1 = new JLabel();
        label1.setText("Bet Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);

        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //panel1.add(spacer1, gbc);

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel2, gbc);

        //type of bet radio buttons
        piazzataRadioButton = new JRadioButton();
        piazzataRadioButton.setText("Placed");
        piazzataRadioButton.setSelected(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(piazzataRadioButton, gbc);

        vincenteRadioButton = new JRadioButton();
        vincenteRadioButton.setText("Winning");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(vincenteRadioButton, gbc);

        //bet amount field
        betAmountField = new JTextField();
        betAmountField.addActionListener(sampleHandler);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(betAmountField, gbc);

        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //panel1.add(spacer2, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("Bet Amount");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 0.2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(label2, gbc);

        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);

        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer4, gbc);

        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer5, gbc);

        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer6, gbc);

        final JLabel label3 = new JLabel();
        label3.setText("Horse Color");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label3, gbc);

        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(panel3, gbc);

        //
        //stable color radio button
        neroRadioButton = new JRadioButton();
        neroRadioButton.setText("Black");
        neroRadioButton.setSelected(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(neroRadioButton, gbc);

        bluRadioButton = new JRadioButton();
        bluRadioButton.setText("Blue");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(bluRadioButton, gbc);

        verdeRadioButton = new JRadioButton();
        verdeRadioButton.setText("Green");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(verdeRadioButton, gbc);

        rossoRadioButton = new JRadioButton();
        rossoRadioButton.setText("Red");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(rossoRadioButton, gbc);

        gialloRadioButton = new JRadioButton();
        gialloRadioButton.setText("Yellow");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(gialloRadioButton, gbc);

        biancoRadioButton = new JRadioButton();
        biancoRadioButton.setText("White");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(biancoRadioButton, gbc);
        //end of stable color radio button
        //

        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        //panel1.add(spacer7, gbc);

        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer8, gbc);

        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer9, gbc);
        betError = new JLabel();
        betError.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.weightx = 0.5;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.WEST;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(betError, gbc);

        registraScommessaBtn = new JButton();
        registraScommessaBtn.setText("Register Bet");
        registraScommessaBtn.addActionListener(sampleHandler);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(registraScommessaBtn, gbc);

        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer10, gbc);

        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer11, gbc);

        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer12, gbc);

        horseColorBtnGroup = new ButtonGroup();
        horseColorBtnGroup.add(neroRadioButton);
        horseColorBtnGroup.add(bluRadioButton);
        horseColorBtnGroup.add(verdeRadioButton);
        horseColorBtnGroup.add(rossoRadioButton);
        horseColorBtnGroup.add(gialloRadioButton);
        horseColorBtnGroup.add(biancoRadioButton);

        betTypeBtnGroup = new ButtonGroup();
        betTypeBtnGroup.add(piazzataRadioButton);
        betTypeBtnGroup.add(vincenteRadioButton);
    }

    /**
     * Updates bet marks of the bet marks pool in the gui at each bet
     *
     * @param betMarkPool hash map representing the bet mark pool to update
     */
    public void updateBetMarks(Map<StableColor, Integer> betMarkPool) {
        for (StableColor color : betMarkPool.keySet()) {
            switch (color) {

                case BLACK:
                    betMarkValue[0].setText(betMarkPool.get(color).toString());
                    break;
                case BLUE:
                    betMarkValue[1].setText(betMarkPool.get(color).toString());

                    break;
                case GREEN:
                    betMarkValue[2].setText(betMarkPool.get(color).toString());

                    break;
                case RED:
                    betMarkValue[3].setText(betMarkPool.get(color).toString());

                    break;
                case YELLOW:
                    betMarkValue[4].setText(betMarkPool.get(color).toString());

                    break;
                case WHITE:
                    betMarkValue[5].setText(betMarkPool.get(color).toString());

                    break;
            }
        }
    }

    /**
     * Builds the bet mark pool panel
     */

    void buildBetMarkPoolPanel() {
        //bet mark pool panel
        betMarkPoolPanel = new JPanel(new GridBagLayout());
        betMarkPoolPanel.setBorder(new TitledBorder("Available Bet Marks"));
        for (int i = 0; i < 6; i++) {
            //BetMark value
            GridBagConstraints c = new GridBagConstraints();

            betMarkValue[i] = new JTextArea();
            betMarkValue[i].setEditable(false);
            betMarkValue[i].append("0");
            c.gridx = i;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_END;
            betMarkPoolPanel.add(betMarkValue[i], c);

            //BetMark Icon
            JTextArea betMarkIcon = new JTextArea();
            betMarkIcon.setEditable(false);
            switch (i) {
                case 0:
                    betMarkIcon.append("Black");
                    break;
                case 1:
                    betMarkIcon.append("Blue");
                    break;
                case 2:
                    betMarkIcon.append("Green");
                    break;
                case 3:
                    betMarkIcon.append("Red");
                    break;
                case 4:
                    betMarkIcon.append("Yellow");
                    break;
                case 5:
                    betMarkIcon.append("White");
                    break;
                default:
                    break;
            }

            c.gridx = i;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_START;
            betMarkPoolPanel.add(betMarkIcon, c);
        }
    }

    /**
     * Create the information panel of every single player
     *
     * @param player owner of the panel
     * @return the created panel
     */

    JPanel createInfoPanelForPlayer(Player player) {
        JPanel playerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        playerPanel.setBorder(new TitledBorder(player.getIdTag()));

        //avatar
        playerCard = new JLabel();
        playerCard.addMouseListener(mouseListener);
        System.out.println(getCharDir() + player.getCharCard().getImagePath());
        ImageIcon charImage = new ImageIcon(getCharDir() + player.getCharCard().getImagePath());
        ImageIcon resizedCharCard = new ImageIcon(getScaledImage(charImage.getImage(), 27, 52));
        playerCard.setIcon(resizedCharCard);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.LINE_START;
        playerPanel.add(playerCard, c);

        //money
        JTextArea money = new JTextArea();
        money.setEditable(false);
        money.append("Gold: " + player.getMoney());
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_END;
        playerPanel.add(money, c);

        //VP
        JTextArea vp = new JTextArea();
        vp.setEditable(false);
        vp.append("VP: " + player.getVictoryPoints());
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_END;
        playerPanel.add(vp, c);

        //vpicon
        JTextArea vpIcon = new JTextArea();
        vpIcon.setEditable(false);
        vpIcon.append("VP Icon");
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_START;
        playerPanel.add(vpIcon, c);

        //stable card
        stableCard = new JLabel();
        stableCard.addMouseListener(mouseListener);
        ImageIcon stableImage = new ImageIcon(getStableDir() + player.getOwnedStables().get(0).getStableCard().getImagePath());
        ImageIcon resizedStableCard = new ImageIcon(getScaledImage(stableImage.getImage(), 27, 52));
        stableCard.setIcon(resizedStableCard);
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.LINE_END;
        playerPanel.add(stableCard, c);

        return playerPanel;
    }


}
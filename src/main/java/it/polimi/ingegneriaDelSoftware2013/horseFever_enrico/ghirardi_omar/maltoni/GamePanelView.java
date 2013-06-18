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
    GamePanelView(GameInterfaceView view) {


        viewRef = view;
        setLayout(new BorderLayout());

        mouseListener = new MouseClickHandler();
        sampleHandler = new ButtonHandler();


        //parte sx della gui
        leftPanel = new JPanel(new BorderLayout());

        //pannello giocatori
        playersBox = Box.createVerticalBox();               //uso Box temporaneamente (grid?)

        leftPanel.add(playersBox, BorderLayout.NORTH);
        //giocatori aggiunti

        //pannello gameLog
        gameLog = new JTextArea(0, 40);
        gameLog.setBorder(new TitledBorder("Game Log"));
        gameLog.setLineWrap(true);
        gameLog.setEditable(false);
        leftPanel.add(gameLog, BorderLayout.CENTER);
        leftPanel.add(new JScrollPane(gameLog), BorderLayout.CENTER);
        initializeGameLog();
        //gameLog aggiunto

        //aggiunge leftPanel al frame
        add(leftPanel, BorderLayout.WEST);

        //crea pannello board...per ora aggiunge solo l'immagine
        midPanel = new BoardPanel(viewRef.getImagedir() + "other/board.jpg");
        midPanel.setMinimumSize(midPanel.getSize());
        /*Icon board = new ImageIcon();
        JLabel imageArea = new JLabel(board);
        //imageArea.setSize(426, 719);
        midPanel.add(imageArea);             */
        //aggiunge midPanel al frame
        add(midPanel, BorderLayout.CENTER);

        //crea rightPanel
        rightPanel = new JPanel();
        Box rightBox = Box.createVerticalBox(); //ordina i componenti del panel;

        //bet mark pool panel
        betMarkValue = new JTextArea[6];
        buildBetMarkPoolPanel();
        betMarkPoolPanel.setVisible(false);
        rightBox.add(betMarkPoolPanel);
        //aggiunti i bet marks;

        buildBetPanel();
        betPanel.setVisible(false);
        rightBox.add(betPanel);
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

        //aggiunti i pulsanti azione

        //lista action card, da pensare, per ora non si aggiunge      */

        //textArea per le descrizioni delle actionCard
        JTextArea actionDescription = new JTextArea(15, 32);
        actionDescription.setLineWrap(true);
        actionDescription.setBorder(new TitledBorder("Action Card Description"));
        rightBox.add(actionDescription);
        //aggiunto ultimo elemento al rightBox

        //aggiungo rightBox a rightPanel
        rightPanel.add(rightBox);
        //infine aggiunge l'ultimo panel
        add(rightPanel, BorderLayout.EAST);

        //creo e aggiungi handler
        //throwDice.addActionListener(sampleHandler);
        //action1.addActionListener(sampleHandler);
        //nextTurn.addActionListener(sampleHandler);

        Dimension size = new Dimension(1500, 980);
        setPreferredSize(size);

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
        //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     * Initializes the game log with a default line
     */

    public void initializeGameLog() {
        this.gameLog.append("Let the game begins!");
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
        hideBetPanels();
    }

    public void clearBetFields() {
        betAmountField.setText("");
        betError.setText("");
        neroRadioButton.setSelected(true);
        piazzataRadioButton.setSelected(true);
    }

    /**
     * Action listener of the register bet button
     */

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == registraScommessaBtn) {
                registerBet();
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
        this.showBetPanels();
        viewRef.pauseGameFlow();


        int amount = Integer.parseInt(betAmountField.getText());
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
        panel1.add(spacer1, gbc);

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
        panel1.add(spacer2, gbc);

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
        panel1.add(spacer7, gbc);

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
        int i = 0;
        for (StableColor color : betMarkPool.keySet()) {
            betMarkValue[i].setText(betMarkPool.get(color).toString());
            System.out.println(betMarkPool.get(color).toString());
            i++;
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
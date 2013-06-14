package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 14/06/13
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */

public class GamePanelView extends JPanel {
    private JPanel leftPanel;
    private JPanel midPanel;
    private JPanel rightPanel;
    private JPanel playerPanels[];
    private JLabel playerCard;
    private JLabel stableCard;
    private JTextArea gameLog;
    private JButton throwDice;
    private JButton action1;
    private JButton nextTurn;
    private GameInterfaceView viewRef;

    //TODO: implementare parte destra, corsa e interazione col modello (che risolvera anche qualche errore di implementazione per ora voluto);
    GamePanelView(GameInterfaceView view) {

        viewRef = view;
        setLayout(new BorderLayout());

        //parte sx della gui
        leftPanel = new JPanel(new BorderLayout());

        //pannello giocatori
        JPanel players = new JPanel();
        Box pBox = Box.createVerticalBox();               //uso Box temporaneamente (grid?)
        players.add(pBox);
        Color defaultColor = players.getBackground();
        MouseClickHandler mouseListener = new MouseClickHandler();
        GridBagConstraints c = new GridBagConstraints();

        //array di JPanels
        playerPanels = new JPanel[6];
        for (int i = 0; i < 6; i++) {  //per ora ciclo for di 6, poi sar‡ parametrizzato con il numero di players
            playerPanels[i] = new JPanel(new GridBagLayout());
            playerPanels[i].setBorder(new TitledBorder("Player" + (i + 1)));

            //avatar
            playerCard = new JLabel();
            playerCard.addMouseListener(mouseListener);
            ImageIcon charImage = new ImageIcon(getCharDir() + "charactercard71.jpg");
            ImageIcon resizedCharCard = new ImageIcon(getScaledImage(charImage.getImage(), 27, 52));
            playerCard.setIcon(resizedCharCard);
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 0.1;
            c.anchor = GridBagConstraints.LINE_START;
            playerPanels[i].add(playerCard, c);

            //soldi
            JTextArea money = new JTextArea();
            money.setEditable(false);
            money.setBackground(defaultColor);
            money.append("Gold: 99999");
            c.gridx = 1;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_END;
            playerPanels[i].add(money, c);

            //VP
            JTextArea vp = new JTextArea();
            vp.setEditable(false);
            vp.setBackground(defaultColor);
            vp.append("VP: 99");
            c.gridx = 2;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_END;
            playerPanels[i].add(vp, c);

            //vpicon
            JTextArea vpIcon = new JTextArea();
            vpIcon.setEditable(false);
            vpIcon.setBackground(defaultColor);
            vpIcon.append("VP Icon");
            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_START;
            playerPanels[i].add(vpIcon, c);

            //stable card
            stableCard = new JLabel();
            stableCard.addMouseListener(mouseListener);
            ImageIcon stableImage = new ImageIcon(getStableDir() + "stablecard55.jpg");
            ImageIcon resizedStableCard = new ImageIcon(getScaledImage(stableImage.getImage(), 27, 52));
            stableCard.setIcon(resizedStableCard);
            c.gridx = 3;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 2;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.LINE_END;
            playerPanels[i].add(stableCard, c);

            pBox.add(playerPanels[i]);

        }

        leftPanel.add(pBox, BorderLayout.NORTH);
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
        midPanel = new JPanel();
        Icon board = new ImageIcon(viewRef.getImagedir() + "board.jpg");
        JLabel imageArea = new JLabel(board);
        //imageArea.setSize(426, 719);
        midPanel.add(imageArea);
        //aggiunge midPanel al frame
        add(midPanel, BorderLayout.CENTER);

        //crea rightPanel
        rightPanel = new JPanel();
        Box rightBox = Box.createVerticalBox(); //ordina i componenti del panel;

        //bet mark pool panel
        JPanel betMarkPoolPanel = new JPanel(new GridBagLayout());
        betMarkPoolPanel.setBorder(new TitledBorder("Available Bet Marks"));
        //crea per ora sei bottoni
        for (int i = 0; i < 6; i++) {
            //BetMark value
            JTextArea betMarkValue = new JTextArea();
            betMarkValue.setEditable(false);
            betMarkValue.setBackground(defaultColor);
            betMarkValue.append("6");
            c.gridx = i;
            c.gridy = 1;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.PAGE_END;
            betMarkPoolPanel.add(betMarkValue, c);

            //BetMark Icon temporanea
            JTextArea betMarkIcon = new JTextArea();
            betMarkIcon.setEditable(false);
            betMarkIcon.setBackground(defaultColor);

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

            /*JButton button = new JButton("Bet Mark Pool"+(i+1));
            betMarkPoolPanel.add(button);   */
        }
        rightBox.add(betMarkPoolPanel);
        //aggiunti i bet marks;

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

        //lista action card, da pensare, per ora non si aggiunge

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
        ButtonHandler sampleHandler = new ButtonHandler();
        throwDice.addActionListener(sampleHandler);
        action1.addActionListener(sampleHandler);
        nextTurn.addActionListener(sampleHandler);

    }

    //resize immagini
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    //metodo per scrivere sul gameLog
    public void initializeGameLog() {
        this.gameLog.append("Let the game begins!");
    }

    //prende l'indirizzo della carta pg
    String getCharDir() {
        return viewRef.getImagedir() + "cards/charactercards/";

    }

    //prende l'indirizzo della carta scuderia
    String getStableDir() {
        return viewRef.getImagedir() + "cards/stablecards/";

    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == throwDice) {
                //tira i dadi e genera riga in gamelog

                /*switch () {
                    case 0:
                        gameLog.append("\nThe BLACK horse sprints!!!"); break;
                    case 1:
                        gameLog.append("\nThe BLUE horse sprints!!!"); break;
                    case 2:
                        gameLog.append("\nThe GREEN horse sprints!!!"); break;
                    case 3:
                        gameLog.append("\nThe RED horse sprints!!!"); break;
                    case 4:
                        gameLog.append("\nThe WHITE horse sprints!!!"); break;
                    case 5:
                        gameLog.append("\nThe YELLOW horse sprints!!!"); break;
                    default:
                        break;
                }     */

            } else if (event.getSource() == action1) {
                gameLog.append("\nDAFUQQQQQ???????");
            } else if (event.getSource() == nextTurn) {
                gameLog.append("\nOnto the next!");
            }
        }
    }

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
}
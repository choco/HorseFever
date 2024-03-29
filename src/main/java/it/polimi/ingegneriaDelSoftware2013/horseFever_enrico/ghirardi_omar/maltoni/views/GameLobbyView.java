package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 04/06/13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class GameLobbyView extends JPanel {

    private ArrayList<String> nicknamesList;

    private JTextField playerNicknameField;
    private JButton addPlayerBtn;
    private JList playersList;
    private JButton backBtn;
    private JButton startGameBtn;
    private GameInterfaceView viewRef;
    private DefaultListModel listModel;

    /**
     * Constructor of a game lobby view object
     *
     * @param view the reference to the main frame of the gui
     */
    GameLobbyView(GameInterfaceView view) {
        viewRef = view;
        nicknamesList = new ArrayList<String>();

        setSize(200, 300);

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8), null));

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(topPanel, gbc);

        playerNicknameField = new JTextField();


        this.addHierarchyListener(new HierarchyListener() {
            @Override
            public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
                playerNicknameField.requestFocus();
            }
        });
        playerNicknameField.requestFocus(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(playerNicknameField, gbc);

        addPlayerBtn = new JButton();
        addPlayerBtn.setText("Add Player");

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        topPanel.add(addPlayerBtn, gbc);

        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(bottomPanel, gbc);

        startGameBtn = new JButton();
        startGameBtn.setText("Start Game!");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        bottomPanel.add(startGameBtn, gbc);
        backBtn = new JButton();
        backBtn.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        bottomPanel.add(backBtn, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(spacer1, gbc);

        final JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(midPanel, gbc);

        playersList = new JList();
        listModel = new DefaultListModel();
        playersList.setModel(listModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 5;
        gbc.ipady = 5;
        midPanel.add(playersList, gbc);

        GameLobbyHandler handler = new GameLobbyHandler();

        addPlayerBtn.addActionListener(handler);
        startGameBtn.addActionListener(handler);
        backBtn.addActionListener(handler);
        playerNicknameField.addActionListener(handler);


        Dimension size = new Dimension(200, 300);
        setPreferredSize(size);
    }

    /**
     * Updates the list of players in the lobby
     */

    void updateList() {
        listModel.clear();

        for (int i = 0; i < nicknamesList.size(); i++) {
            listModel.add(i, nicknamesList.get(i));
        }
    }

    /**
     * Action listener to perform actions in the game lobby screen
     */

    private class GameLobbyHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == addPlayerBtn || event.getSource() == playerNicknameField) {
                String tmp = playerNicknameField.getText();
                if (!tmp.equals("")) {
                    nicknamesList.add(tmp);
                    updateList();
                    playerNicknameField.setText("");
                }

            } else if (event.getSource() == startGameBtn) {
                if (nicknamesList.size() < 2) {
                    JOptionPane.showMessageDialog(null, "At least two players are needed!", "Error", JOptionPane.INFORMATION_MESSAGE);
                } else if (nicknamesList.size() > 6) {
                    JOptionPane.showMessageDialog(null, "Too many players! Six is the maximum number of players allowed.\nThe others we'll have to cheer!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    int numberOfPlayersToRemove = nicknamesList.size() - 6;
                    for (int i = 0; i < numberOfPlayersToRemove; i++) {
                        nicknamesList.remove(nicknamesList.size() - 1);
                    }
                    updateList();
                } else {
                    viewRef.startMatchWithPlayers(nicknamesList);
                }
            } else if (event.getSource() == backBtn) {
                viewRef.setGameMenuAsMainPanel();
            }


        }
    }


}

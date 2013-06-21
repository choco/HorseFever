package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.views;

import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.RaceManager;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.interfaces.RaceInterface;
import it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni.models.Horse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 16/06/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class BoardPanel extends JPanel implements RaceInterface {

    RaceManager manager;

    private Image img;
    private Image blackHorse;
    private Image blueHorse;
    private Image greenHorse;
    private Image redHorse;
    private Image whiteHorse;
    private Image yellowHorse;

    private int xCoordinate[];
    private int yCoordinate[];

    private static final int yMovement[] = {200, 250, 295, 340, 375, 420, 460, 495, 525, 555, 585, 620, 650, 675, 705, 730, 760, 780};
    private static final int xMovement[] = {35, 45, 55, 65, 75, 75, 89, 93, 97, 101, 105, 109, 114, 120, 124, 128, 132, 136};

    /**
     * Constructor of a board panel object
     *
     * @param img image path of the board image to paint
     */
    public BoardPanel(String img, RaceManager manager) {
        this(new ImageIcon(img).getImage(), manager);
    }

    /**
     * Constructor of a board panel object
     *
     * @param img image type variable to paint in the panel
     */

    public BoardPanel(Image img, RaceManager manager) {
        this.img = img;
        this.manager = manager;

        manager.setRaceInterface(this);
        xCoordinate = new int[6];
        for (int i = 0; i < 6; i++) {
            xCoordinate[i] = xMovement[0];
        }
        yCoordinate = new int[6];
        for (int i = 0; i < 6; i++) {
            yCoordinate[i] = yMovement[0];
        }


        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        blackHorse = new ImageIcon("rsc/horses/blackHorse.jpg").getImage();
        blueHorse = new ImageIcon("rsc/horses/blueHorse.jpg").getImage();
        greenHorse = new ImageIcon("rsc/horses/greenHorse.jpg").getImage();
        redHorse = new ImageIcon("rsc/horses/redHorse.jpg").getImage();
        whiteHorse = new ImageIcon("rsc/horses/whiteHorse.jpg").getImage();
        yellowHorse = new ImageIcon("rsc/horses/yellowHorse.jpg").getImage();


        setPreferredSize(size);
        setMinimumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    /**
     * Paint the image
     *
     * @param g graphics object needed to draw the image
     */

    public void setHorsesPosition(ArrayList<Horse> horses) {
        for (Horse horse : horses) {
            int position = 0;
            position = horse.getCurrentPosition();
            if (position > 16)
                position = 16;

            switch (horse.getOwnerStable().getColor()) {

                case BLACK:
                    xCoordinate[0] = xMovement[position];
                    yCoordinate[0] = yMovement[position];
                    break;

                case BLUE:
                    xCoordinate[1] = xMovement[position];
                    yCoordinate[1] = yMovement[position];
                    break;

                case GREEN:
                    xCoordinate[2] = xMovement[position];
                    yCoordinate[2] = yMovement[position];
                    break;

                case RED:
                    xCoordinate[3] = xMovement[position];
                    yCoordinate[3] = yMovement[position];
                    break;

                case YELLOW:
                    xCoordinate[4] = xMovement[position];
                    yCoordinate[4] = yMovement[position];
                    break;

                case WHITE:
                    xCoordinate[5] = xMovement[position];
                    yCoordinate[5] = yMovement[position];
                    break;
            }
        }

        repaint();

    }

    public void paintComponent(Graphics g) {

        Dimension size = getSize();
        g.drawImage(img, 0, 0, size.width, size.height, null);

        g.drawImage(blackHorse, xCoordinate[0], size.height - yCoordinate[0], 25, 25, null);

        g.drawImage(blueHorse, xCoordinate[1] + 80, size.height - yCoordinate[1], 25, 25, null);

        g.drawImage(greenHorse, 250, size.height - yCoordinate[2], 25, 25, null);

        g.drawImage(redHorse, 320, size.height - yCoordinate[3], 25, 25, null);

        g.drawImage(yellowHorse, size.width - (xCoordinate[4] + 80 + 30), size.height - yCoordinate[4], 25, 25, null);

        g.drawImage(whiteHorse, size.width - (xCoordinate[5] + 30), size.height - yCoordinate[5], 25, 25, null);


    }

    public void updateHorsesPositions(ArrayList<Horse> horsesList) {
        setHorsesPosition(horsesList);

    }
}

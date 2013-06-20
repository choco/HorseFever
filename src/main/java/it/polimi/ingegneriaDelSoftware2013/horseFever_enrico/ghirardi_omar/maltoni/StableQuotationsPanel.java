package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 20/06/13
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class StableQuotationsPanel extends JPanel {

    private Image img;

    private Image blackHorse;
    private Image blueHorse;
    private Image greenHorse;
    private Image redHorse;
    private Image whiteHorse;
    private Image yellowHorse;

    private static final int xQuotation[] = {260, 220, 180, 140, 100, 60};
    private static final int yQuotation[] = {17, 46, 75, 104, 133, 162};

    private int xCoordinate[];
    private int yCoordinate[];

    public StableQuotationsPanel(String img) {
        this(new ImageIcon(img).getImage());


    }

    /**
     * Constructor of a board panel object
     *
     * @param img image type variable to paint in the panel
     */

    public StableQuotationsPanel(Image img) {
        this.img = img;


        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));

        blackHorse = new ImageIcon("rsc/horses/blackHorse.jpg").getImage();
        blueHorse = new ImageIcon("rsc/horses/blueHorse.jpg").getImage();
        greenHorse = new ImageIcon("rsc/horses/greenHorse.jpg").getImage();
        redHorse = new ImageIcon("rsc/horses/redHorse.jpg").getImage();
        whiteHorse = new ImageIcon("rsc/horses/whiteHorse.jpg").getImage();
        yellowHorse = new ImageIcon("rsc/horses/yellowHorse.jpg").getImage();

        xCoordinate = new int[6];
        yCoordinate = new int[6];

        for (int i = 0; i < 6; i++) {
            xCoordinate[i] = xQuotation[0];
        }

        for (int i = 0; i < 6; i++) {
            yCoordinate[i] = yQuotation[i];
        }


        setPreferredSize(size);
        setMinimumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    public void updateStableQuotations(ArrayList<Stable> stables) {
        int numberOfStablesPerQuotation[] = new int[6];
        for (int i = 0; i < 6; i++) {
            numberOfStablesPerQuotation[i] = 0;
        }


        for (Stable stable : stables) {
            System.out.println("La quotazione della scuderia " + stable.getColor() + " è " + stable.getQuotation());
            int quotation = stable.getQuotation() - 2;
            switch (stable.getColor()) {

                case BLACK:
                    yCoordinate[0] = yQuotation[quotation];
                    xCoordinate[0] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case BLUE:
                    yCoordinate[1] = yQuotation[quotation];
                    xCoordinate[1] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case GREEN:
                    yCoordinate[2] = yQuotation[quotation];
                    xCoordinate[2] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case RED:
                    yCoordinate[3] = yQuotation[quotation];
                    xCoordinate[3] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case YELLOW:
                    yCoordinate[4] = yQuotation[quotation];
                    xCoordinate[4] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case WHITE:
                    yCoordinate[5] = yQuotation[quotation];
                    xCoordinate[5] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
            }
            (numberOfStablesPerQuotation[quotation])++;
        }

        repaint();

    }

    public void paintComponent(Graphics g) {

        Dimension size = getSize();
        g.drawImage(img, 0, 0, size.width, size.height, null);

        g.drawImage(blackHorse, xCoordinate[0], yCoordinate[0], 25, 25, null);

        g.drawImage(blueHorse, xCoordinate[1], yCoordinate[1], 25, 25, null);

        g.drawImage(greenHorse, xCoordinate[2], yCoordinate[2], 25, 25, null);

        g.drawImage(redHorse, xCoordinate[3], yCoordinate[3], 25, 25, null);

        g.drawImage(yellowHorse, xCoordinate[4], yCoordinate[4], 25, 25, null);

        g.drawImage(whiteHorse, xCoordinate[5], yCoordinate[5], 25, 25, null);
    }
}

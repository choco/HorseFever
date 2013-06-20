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

    private int xCordinate[];
    private int yCordinate[];

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

        xCordinate = new int[6];
        yCordinate = new int[6];

        for (int i = 0; i < 6; i++) {
            xCordinate[i] = xQuotation[0];
        }

        for (int i = 0; i < 6; i++) {
            yCordinate[i] = yQuotation[i];
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
                    yCordinate[0] = yQuotation[quotation];
                    xCordinate[0] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case BLUE:
                    yCordinate[1] = yQuotation[quotation];
                    xCordinate[1] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case GREEN:
                    yCordinate[2] = yQuotation[quotation];
                    xCordinate[2] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case RED:
                    yCordinate[3] = yQuotation[quotation];
                    xCordinate[3] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case YELLOW:
                    yCordinate[4] = yQuotation[quotation];
                    xCordinate[4] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
                case WHITE:
                    yCordinate[5] = yQuotation[quotation];
                    xCordinate[5] = xQuotation[numberOfStablesPerQuotation[quotation]];
                    break;
            }
            (numberOfStablesPerQuotation[quotation])++;
        }

        repaint();

    }

    public void paintComponent(Graphics g) {

        Dimension size = getSize();
        g.drawImage(img, 0, 0, size.width, size.height, null);

        g.drawImage(blackHorse, xCordinate[0], yCordinate[0], 25, 25, null);

        g.drawImage(blueHorse, xCordinate[1], yCordinate[1], 25, 25, null);

        g.drawImage(greenHorse, xCordinate[2], yCordinate[2], 25, 25, null);

        g.drawImage(redHorse, xCordinate[3], yCordinate[3], 25, 25, null);

        g.drawImage(yellowHorse, xCordinate[4], yCordinate[4], 25, 25, null);

        g.drawImage(whiteHorse, xCordinate[5], yCordinate[5], 25, 25, null);
    }
}

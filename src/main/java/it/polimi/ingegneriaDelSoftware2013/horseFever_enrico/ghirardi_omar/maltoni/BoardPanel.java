package it.polimi.ingegneriaDelSoftware2013.horseFever_enrico.ghirardi_omar.maltoni;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cHoco
 * Date: 16/06/13
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */
public class BoardPanel extends JPanel {

    private Image img;

    /**
     * Constructor of a board panel object
     * @param img image path of the board image to paint
     */
    public BoardPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    /**
     * Constructor of a board panel object
     * @param img image type variable to paint in the panel
     */

    public BoardPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    /**
     * Paint the image
     * @param g graphics object needed to draw the image
     */

    public void paintComponent(Graphics g) {
        Dimension size = getSize();
        g.drawImage(img, 0, 0, size.width, size.height, null);
    }
}

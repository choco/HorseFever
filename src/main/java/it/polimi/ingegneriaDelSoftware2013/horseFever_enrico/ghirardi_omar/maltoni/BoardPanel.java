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

    public BoardPanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public BoardPanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMinimumSize(size);
        setSize(size);
    }

    public void paintComponent(Graphics g) {
        Dimension size = getSize();
        g.drawImage(img, 0, 0, size.width, size.height, null);
    }
}

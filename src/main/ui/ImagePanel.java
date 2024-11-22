package ui;

import javax.swing.*;
import java.awt.*;

// ** SOURCE I REFERENCED TO HELP WITH THIS PHASE!!! ** //
// Link: https://www.youtube.com/watch?v=Kmgo00avvEw //
// A bit long but went real in depth to many different components //
// of swing and made this whole phase way easier than hitting //
// stackoverflow every 5 minutes //

public class ImagePanel extends JPanel {

    private Image image;

    // EFFECTS: loads the image to be displayed
    public ImagePanel() {
        ImageIcon icon = new ImageIcon("./ProjectStarter/data/stonkscombined.jpg");
        image = icon.getImage();
        setPreferredSize(new Dimension(800, 250));
    }

    // EFFECTS: paints the image onto the panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

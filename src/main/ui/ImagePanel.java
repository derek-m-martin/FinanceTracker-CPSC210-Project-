package ui;

import javax.swing.*;
import java.awt.*;

// A custom panel to display an image (visual component)
public class ImagePanel extends JPanel {

    private Image image;

    // EFFECTS: loads the image to be displayed
    public ImagePanel() {
        ImageIcon icon = new ImageIcon("tbd until i mock up an image");
        image = icon.getImage();
        setPreferredSize(new Dimension(800, 100));
    }

    // EFFECTS: paints the image onto the panel
    protected void paintComponent(Graphics g) {
    }
}

package view.helpers;

import javax.swing.*;
import java.awt.*;

public class CustomActionButton extends JButton {
    private final Dimension arcs = new Dimension(10,10);

    public CustomActionButton(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
    }
}

package view.helpers;

import javax.swing.*;
import java.awt.*;

public class CustomActionButton extends JButton {

    public CustomActionButton(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.INSERTED_BACKGROUND);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.PRIMARY_COLOR);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
    }
}

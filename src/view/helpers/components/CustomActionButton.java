package view.helpers.components;

import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Locale;

public class CustomActionButton extends JButton {

    public CustomActionButton(String text) {
        super(text.toUpperCase(Locale.ROOT));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setFont(GlobalStyles.MAIN_FONT);
        setForeground(GlobalStyles.BTN_PRIMARY_COLOR);
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.BTN_HOVER_COLOR);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
            }
        });
        addFocusListener(new ButtonCustomFocusListener());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
    }

    private static class ButtonCustomFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            CustomActionButton source = (CustomActionButton) e.getSource();
            source.setBackground(GlobalStyles.BTN_HOVER_COLOR);
        }

        @Override
        public void focusLost(FocusEvent e) {
            CustomActionButton source = (CustomActionButton) e.getSource();
            source.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        }
    }
}

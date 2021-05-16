package view.helpers.components;

import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextInput extends JTextField {
    public TextInput() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR, 2),
                BorderFactory.createEmptyBorder(2,5,2,5)));
        setFont(GlobalStyles.MAIN_FONT);
        addFocusListener(new TextFieldFocusListener());
    }

    private class TextFieldFocusListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_FOCUS_COLOR, 2),
                    BorderFactory.createEmptyBorder(2,5,2,5)));
        }

        @Override
        public void focusLost(FocusEvent e) {
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR, 2),
                    BorderFactory.createEmptyBorder(2,5,2,5)));
        }
    }
}

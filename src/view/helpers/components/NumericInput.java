package view.helpers.components;

import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class NumericInput extends JSpinner {
    public NumericInput(SpinnerModel model) {
        super(model);
        setBorder(BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR, 2));
        setFont(GlobalStyles.MAIN_FONT);
        addFocusListener(new NumericInputFocusListener());
    }

    private class NumericInputFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_FOCUS_COLOR, 2));
        }

        @Override
        public void focusLost(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR, 2));
        }
    }
}

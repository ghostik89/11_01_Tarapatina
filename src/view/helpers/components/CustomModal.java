package view.helpers.components;

import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class CustomModal extends JDialog {
    private final GridBagConstraints constraints = new GridBagConstraints();
    private final JLabel text;
    private int gridBtnCounter = 0;

    public CustomModal(JFrame owner, JLabel message){
        super(owner, "", true);
        setLocation(520,300);
        setResizable(false);
        setSize(new Dimension(500,200));
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        this.text = message;

        setLayout(new GridBagLayout());
        constraints.insets = new Insets(20,20,20,20);
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(message, constraints);
    }

    public void addButton(JButton button){
        constraints.fill = GridBagConstraints.PAGE_END;
        constraints.weighty = 1.0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridx = this.gridBtnCounter++;
        constraints.anchor = GridBagConstraints.SOUTHEAST;
        add(button, constraints);
    }

    public void setMessage(String message){this.text.setText(message);}
}

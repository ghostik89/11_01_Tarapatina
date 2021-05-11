package view;

import model.Alphabet;
import view.helpers.CustomActionButton;

import javax.swing.*;

public class AlphabetWidget extends JDialog {
    private Alphabet alphabet;
    private JButton acceptBtn = new CustomActionButton("Подтвердить");
    private JButton cancelBtn = new CustomActionButton("Отменить");
}

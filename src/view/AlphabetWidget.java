package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Alphabet;
import view.helpers.GlobalStyles;
import view.helpers.factories.CustomActionButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlphabetWidget extends JDialog {
    private final Alphabet alphabet;
    private final JPanel alphabetGrid = new JPanel();
    private char selectedChar;
    private final ArrayList<PlayerActionFieldListener> actionFieldEventList = new ArrayList<>();


    public AlphabetWidget(JFrame owner, Alphabet alphabet) {
        super(owner, "Alphabet keyboard", true);
        this.alphabet = alphabet;

        setLocation(520,300);
        setSize(new Dimension(500, 500));
        setResizable(false);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new BorderLayout(10,10));

        alphabetGrid.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        initAlphabetCells();
        add(this.alphabetGrid, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(Box.createHorizontalGlue());
        JButton cancelBtn = CustomActionButtonFactory.createButtonWithoutBorder("отменить");
        controlPanel.add(cancelBtn);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton acceptBtn = CustomActionButtonFactory.createButtonWithoutBorder("подтвердить");
        controlPanel.add(acceptBtn);
        controlPanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        cancelBtn.addActionListener(e -> setVisible(false));
        acceptBtn.addActionListener(e -> {
            setVisible(false);
            this.fireLetterIsChoose();
        });

        add(controlPanel, BorderLayout.PAGE_END);
    }

    public char getSelectedChar() {
        return selectedChar;
    }

    private void initAlphabetCells(){
        alphabetGrid.setPreferredSize(new Dimension(500,300));
        alphabetGrid.setLayout(new GridLayout(6, 6, 4, 4));
        for(char ch : this.alphabet.getCurrentAlphabet().toCharArray()){
            alphabetGrid.add(new LetterCellWidget(ch,15, this));
        }
    }

    public void setSelectedChar(char selectedChar) {
        this.selectedChar = selectedChar;
    }

    void addListener(PlayerActionFieldListener listener){
        this.actionFieldEventList.add(listener);
    }

    private void fireLetterIsChoose(){
        PlayerActionFieldEvent event = new PlayerActionFieldEvent(this);
        event.setLetter(this.selectedChar);
        this.actionFieldEventList.forEach(e -> e.playerClickOnAlphabet(event));
    }
}

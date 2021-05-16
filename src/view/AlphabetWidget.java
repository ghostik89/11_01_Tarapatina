package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Alphabet;
import model.Cell;
import view.helpers.CustomActionButton;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlphabetWidget extends JDialog {
    private final Alphabet alphabet;
    private final JPanel alphabetGrid = new JPanel();
    char selectedChar;
    private final ArrayList<PlayerActionFieldListener> actionFieldEventList = new ArrayList<>();


    public AlphabetWidget(JFrame owner, Alphabet alphabet) {
        super(owner, "Alphabet keyboard", true);
        this.alphabet = alphabet;

        setLocation(520,300);
        setSize(new Dimension(600, 600));
        setResizable(false);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new BorderLayout(10,10));

        initAlphabetCells();
        add(this.alphabetGrid, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.add(Box.createHorizontalGlue());
        JButton cancelBtn = new CustomActionButton("Отменить");
        controlPanel.add(cancelBtn);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton acceptBtn = new CustomActionButton("Подтвердить");
        controlPanel.add(acceptBtn);

        cancelBtn.addActionListener(e -> setVisible(false));
        acceptBtn.addActionListener(e -> {
            setVisible(false);
            this.fireLetterIsChoose();
        });

        add(controlPanel, BorderLayout.PAGE_END);
    }

    private void initAlphabetCells(){
        int size = this.alphabet.getCurrentAlphabet().length() / 2;
        alphabetGrid.setLayout(new GridLayout(size, size, 4, 4));
        for(char ch : this.alphabet.getCurrentAlphabet().toCharArray()){
            alphabetGrid.add(new LetterCellWidget(ch,size, this));
        }
    }

    public char getSelectedChar() {
        return selectedChar;
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

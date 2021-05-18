package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Alphabet;
import view.helpers.components.CustomActionButton;
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
        setSize(new Dimension(500, 500));
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

package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Alphabet;
import model.BlockedAlphabet;
import view.helpers.GlobalStyles;
import view.helpers.factories.CustomActionButtonFactory;
import view.helpers.factories.DialogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AlphabetWidget extends JDialog {
    private final Alphabet alphabet;
    private final JPanel alphabetGrid = new JPanel();
    private JFrame owner;
    private char selectedChar;
    private final ArrayList<PlayerActionFieldListener> actionFieldEventList = new ArrayList<>();


    public AlphabetWidget(JFrame owner, Alphabet alphabet, GameWidget gameWidget) {
        super(owner, "Alphabet keyboard", true);
        this.owner = owner;
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

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            gameWidget.getGame().revertState();
            gameWidget.repaint();
        });

        acceptBtn.addActionListener(e -> {
            setVisible(false);
            this.fireLetterIsChoose();
            this.selectedChar = '\0';
        });

        add(controlPanel, BorderLayout.PAGE_END);

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                gameWidget.getGame().revertState();
                gameWidget.repaint();
                super.windowClosing(e);
            }
        });
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
         if(((BlockedAlphabet)this.alphabet).getBlockedChars().contains(String.valueOf(selectedChar)))
            DialogFactory.createBasicInfoSnackbar("Буква заблокирована", this.owner).setVisible(true);
        else
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

    Alphabet getAlphabet(){
        return this.alphabet;
    }
}

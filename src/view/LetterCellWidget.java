package view;
import model.CustomizedAlphabet;
import view.helpers.components.CustomActionButton;
import view.helpers.GlobalStyles;

import java.awt.*;

public class LetterCellWidget extends CustomActionButton {
    private AlphabetWidget owner;
    private final char myChar;

    public LetterCellWidget(char text, int fieldSize, AlphabetWidget owner) {
        super(String.valueOf(text));
        this.owner = owner;
        int fontSize;
        fontSize = switch (fieldSize){
            case 5 -> 60;
            case 6 -> 55;
            case 7 -> 45;
            case 8 -> 35;
            default -> 30;
        };
        this.myChar = text;
        setFont(new Font(GlobalStyles.fontName, Font.PLAIN, fontSize));
        addActionListener(e -> {
            this.owner.setSelectedChar(this.myChar);
            this.owner.repaint();
            this.owner.revalidate();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(this.myChar == this.owner.getSelectedChar()?
                GlobalStyles.SELECTED_CELL : GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        try{
            if(this.myChar == this.owner.getSelectedChar()) {
                setBackground(GlobalStyles.SELECTED_CELL);
                setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            else if(((CustomizedAlphabet)this.owner.getAlphabet()).getBlockedChars().contains(String.valueOf(myChar))){
                setBackground(GlobalStyles.CELL_BLOCKED);
                setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }else{
                setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
                setForeground(GlobalStyles.PRIMARY_TEXT_COLOR);
            }
        }catch (ClassCastException ex){
            if(this.myChar == this.owner.getSelectedChar()) {
                setBackground(GlobalStyles.SELECTED_CELL);
                setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }else{
                setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
                setForeground(GlobalStyles.PRIMARY_TEXT_COLOR);
            }
        }
    }
}

package view;
import view.helpers.components.CustomActionButton;
import view.helpers.GlobalStyles;
import java.awt.*;

public class LetterCellWidget extends CustomActionButton {
    private final AlphabetWidget owner;
    private final char myChar;

    public LetterCellWidget(char text, int fieldSize, AlphabetWidget owner) {
        super(String.valueOf(text));
        this.owner = owner;
        int fontSize;
        switch (fieldSize){
            case 5 -> fontSize = 60;
            case 6 -> fontSize = 55;
            case 7 -> fontSize = 45;
            case 8 -> fontSize = 35;
            default -> fontSize = 30;
        }
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
        setForeground(this.myChar == this.owner.getSelectedChar()? GlobalStyles.SECONDARY_TEXT_COLOR :
                GlobalStyles.PRIMARY_TEXT_COLOR);
    }
}

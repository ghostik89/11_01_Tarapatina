package view;

import model.CellState;
import view.helpers.CustomActionButton;
import java.awt.*;

public class CellWidget extends CustomActionButton {

    public CellWidget(int fieldSize, char initLetter){
        super("");
        int fontSize;
        switch (fieldSize){
            case 5 -> fontSize = 60;
            case 6 -> fontSize = 55;
            case 7 -> fontSize = 45;
            case 8 -> fontSize = 35;
            default -> fontSize = 30;
        }
        setFont(new Font("Roboto", Font.PLAIN, fontSize));
        this.setLetter(initLetter);
    }

    public void setBackground(CellState state){}

    public void setLetter(char letter) {
        setText(String.valueOf(letter));
    }
}

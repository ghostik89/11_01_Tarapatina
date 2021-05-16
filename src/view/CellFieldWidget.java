package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Cell;
import view.helpers.components.CustomActionButton;
import view.helpers.GlobalStyles;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class CellFieldWidget extends CustomActionButton {
    private final Cell member;
    private final ArrayList<PlayerActionFieldListener> actionFieldEventList = new ArrayList<>();


    public CellFieldWidget(int fieldSize, Cell cell){
        super("");
        this.member = cell;

        int fontSize;
        switch (fieldSize){
            case 5 -> fontSize = 60;
            case 6 -> fontSize = 55;
            case 7 -> fontSize = 45;
            case 8 -> fontSize = 35;
            default -> fontSize = 30;
        }
        setFont(new Font(GlobalStyles.fontName, Font.PLAIN, fontSize));
        addActionListener(e -> this.fireCellIsSelected());
    }

    void addListener(PlayerActionFieldListener listener){
        this.actionFieldEventList.add(listener);
    }

    private void fireCellIsSelected(){
        PlayerActionFieldEvent event = new PlayerActionFieldEvent(this);
        event.setPoint(this.member.getCellPosition());
        this.actionFieldEventList.forEach(e -> e.playerClickToField(event));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText(String.valueOf(this.member.getLetter()));

        switch (this.member.getCellState()){
            case CELL_WITH_SETTED_LETTER_AT_TURN,CELL_SELECTED_FOR_INSERTING ->
                    setBackground(GlobalStyles.INSERTED_BACKGROUND);
            case CELL_IS_SELECTED -> {
                setBackground(GlobalStyles.SELECTED_CELL);
                //todo try to override
                setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
            }
            default -> setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        }
    }
}

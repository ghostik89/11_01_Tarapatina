package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.BlockedCell;
import model.Cell;
import view.helpers.components.CustomActionButton;
import view.helpers.GlobalStyles;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CellFieldWidget extends JPanel {
    private final Cell member;
    private final ArrayList<PlayerActionFieldListener> actionFieldEventList = new ArrayList<>();
    private final JLabel charLabel = StyledLabelFactory.createBasicLabel("");
    private final JLabel indexLabel = StyledLabelFactory.createBasicLabel("");


    public CellFieldWidget(int fieldSize, Cell cell){
        this.member = cell;
        setLayout(new BorderLayout());
        int fontSize;
        fontSize = switch (fieldSize){
            case 5 -> 60;
            case 6 -> 55;
            case 7 -> 45;
            case 8 -> 35;
            default -> 30;
        };

        addFocusListener(new CellWidgetFocusListener());

        charLabel.setFont(new Font(GlobalStyles.fontName, Font.PLAIN, fontSize));
        charLabel.setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GlobalStyles.CELL_BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(5,5,5,5)));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fireCellIsSelected();
            }
        });
        add(this.indexLabel, BorderLayout.PAGE_START);
        add(this.charLabel, BorderLayout.CENTER);
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
        charLabel.setText(String.valueOf(this.member.getLetter()));

        System.out.println(this.member.getCellState());
        switch (this.member.getCellState()){
            case CELL_WITH_SETTED_LETTER_AT_TURN,CELL_SELECTED_FOR_INSERTING -> {
                setBackground(GlobalStyles.INSERTED_BACKGROUND);
                indexLabel.setText("");
                charLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            case CELL_IS_SELECTED -> {
                indexLabel.setText((this.member.getSelectedIndex() + 1) + ".");
                setBackground(GlobalStyles.SELECTED_CELL);
                charLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
                indexLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            case CELL_IS_BLOCKED -> {
                setBackground(GlobalStyles.CELL_BLOCKED);
                charLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            default -> {
                indexLabel.setText("");
                setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
                charLabel.setForeground(GlobalStyles.PRIMARY_TEXT_COLOR);
                indexLabel.setForeground(GlobalStyles.PRIMARY_TEXT_COLOR);
            }
        }
    }

    private static class CellWidgetFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            CustomActionButton source = (CustomActionButton) e.getSource();
            source.setBackground(GlobalStyles.BTN_HOVER_COLOR);
        }

        @Override
        public void focusLost(FocusEvent e) {
            CustomActionButton source = (CustomActionButton) e.getSource();
            source.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        }
    }
}

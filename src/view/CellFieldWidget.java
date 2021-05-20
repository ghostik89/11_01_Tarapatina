package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
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
    JLabel charLabel = StyledLabelFactory.createBasicLabel("");
    JLabel indexLabel = StyledLabelFactory.createBasicLabel("");


    public CellFieldWidget(int fieldSize, Cell cell){
        this.member = cell;
        setLayout(new BorderLayout());
        int fontSize;
        switch (fieldSize){
            case 5 -> fontSize = 60;
            case 6 -> fontSize = 55;
            case 7 -> fontSize = 45;
            case 8 -> fontSize = 35;
            default -> fontSize = 30;
        }

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

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.BTN_HOVER_COLOR);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
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

        switch (this.member.getCellState()){
            case CELL_WITH_SETTED_LETTER_AT_TURN,CELL_SELECTED_FOR_INSERTING -> {
                setBackground(GlobalStyles.INSERTED_BACKGROUND);
                charLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            case CELL_IS_SELECTED -> {
                indexLabel.setText((this.member.getSelectedIndex() + 1) + ".");
                setBackground(GlobalStyles.SELECTED_CELL);
                charLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
                indexLabel.setForeground(GlobalStyles.SECONDARY_TEXT_COLOR);
            }
            default -> {
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

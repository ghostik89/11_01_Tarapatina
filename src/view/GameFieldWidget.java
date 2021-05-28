package view;
import model.GameField;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class GameFieldWidget extends JPanel {

    public GameFieldWidget(GameWidget owner, GameWidget.PlayerActionObserver observer) {
        setPreferredSize(new Dimension(790, 490));
        GameField field = owner.getGame().getField();
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);


        setLayout(new GridLayout(field.getHeight(), field.getWidth(), 4, 4));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GlobalStyles.CELL_BORDER_COLOR, 1),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        for(var i = 0; i < field.getHeight(); i++)
            for (var j = 0; j < field.getWidth(); j++) {
                var cell = new CellFieldWidget(field.getHeight(), field.getCellByPoint(new Point(i,j)));
                cell.addListener(observer);
                add(cell);
            }
    }
}

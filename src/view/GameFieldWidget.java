package view;
import model.GameField;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFieldWidget extends JPanel {
    private final GameWidget owner;
    private ArrayList<CellFieldWidget> cellFieldWidgets = new ArrayList<>();

    public GameFieldWidget(GameWidget owner, GameWidget.PlayerActionObserver observer) {
        this.owner = owner;
        setPreferredSize(new Dimension(790, 490));
        GameField field = this.owner.getGame().getField();


        setLayout(new GridLayout(field.getWidth(), field.getHeight(), 4, 4));
        for(int i = 0; i < field.getWidth(); i++)
            for (int j = 0; j < field.getHeight(); j++) {
                CellFieldWidget cell = new CellFieldWidget(field.getHeight(), field.getCellByPoint(new Point(i,j)));
                cell.addListener(observer);
                add(cell);
                this.cellFieldWidgets.add(cell);
            }
    }
}

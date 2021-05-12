package view;

import model.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameFieldWidget extends JPanel {
    private final GameWidget owner;
    private ArrayList<CellWidget> cellWidgets = new ArrayList<>();

    public GameFieldWidget(GameWidget owner) {
        this.owner = owner;
        GameField field = this.owner.getGame().getField();


        setLayout(new GridLayout(field.getWidth(), field.getHeight(), 4, 4));
        for(int i = 0; i < field.getWidth(); i++)
            for (int j = 0; j < field.getHeight(); j++) {
                CellWidget cell = new CellWidget(field.getHeight(), field.getCellByPoint(new Point(i,j)).getLetter());
                cell.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //todo add logic
                    }
                });
                add(cell);
                this.cellWidgets.add(cell);
            }
    }
}

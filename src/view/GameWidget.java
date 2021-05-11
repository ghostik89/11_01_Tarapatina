package view;

import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private ArrayList<CellWidget> cellWidgets = new ArrayList<>();

    public GameWidget(MainWindow owner){
        this.owner = owner;
        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setVisible(false);
    }

    public void initField(){
        GameField field = this.game.getField();
        setLayout(new GridLayout(field.getWidth(), field.getHeight(), 4, 4));

        for(int i = 0; i < field.getWidth(); i++)
            for (int j = 0; j < field.getHeight(); j++){
                CellWidget cell = new CellWidget(field.getHeight(), field.getCellByPoint(new Point(i,j)).getLetter());
                cell.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        //todo add logic
//                        if (_owner.getGame().activePlayer().selectCell(finalI, finalJ))
//                            cell.setSelection(field.getCell(finalI, finalJ).selectionState());
                    }
                });
                add(cell);
                this.cellWidgets.add(cell);
            }
        setVisible(true);
    }

    public void setGame(@NotNull Game game) {
        this.game = game;
    }
}

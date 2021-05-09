package view;

import model.Game;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;

    public GameWidget(MainWindow owner){
        this.owner = owner;
        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setVisible(false);
    }

    public void initField(){}

    public void setGame(@NotNull Game game) {
        this.game = game;
    }
}

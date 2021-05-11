package view;

import model.Game;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainWindow extends JFrame {
    private final StartMenuWidget startMenu = new StartMenuWidget(this);
    private final GameWidget gameWidget = new GameWidget(this);

    public MainWindow(){
        setTitle("Balda. The game");
        setSize(new Dimension(1000, 720));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        add(startMenu, gbc);
        add(gameWidget,gbc);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    void runGame(@NotNull Game game){
        this.gameWidget.setGame(game);
        this.gameWidget.initField();
        this.gameWidget.setVisible(true);
    }
}

package view;

import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainWindow extends JFrame {
    private final StartMenuWidget startMenu = new StartMenuWidget(this);
    private final GameWidget gameWidget = new GameWidget(this);

    public MainWindow(){
        setTitle("Balda. The game");
        setSize(new Dimension(1000, 720));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //todo add yet windows
        add(startMenu, gbc);
        add(gameWidget,gbc);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    //todo create route to main menu
    void runGame(@NotNull Game game){
        gameWidget.setGame(game);
        gameWidget.setVisible(true);
    }
}

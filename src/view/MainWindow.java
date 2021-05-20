package view;

import model.Game;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final StartMenuWidget startMenu = new StartMenuWidget(this);
    private final GameWidget gameWidget = new GameWidget(this);

    public MainWindow(){
        setTitle("Balda. The game");
        setSize(new Dimension(1300, 800));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new GridBagLayout());

        add(this.gameWidget, this.gbc);
        add(this.startMenu, this.gbc);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void runGame(@NotNull Game game){
        this.gameWidget.setGame(game);
        this.gameWidget.initField();
        this.gameWidget.setVisible(true);
    }

    void revertGame(){
        this.gameWidget.setVisible(false);
        this.startMenu.setVisible(true);
    }
}

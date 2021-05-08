package view;

import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class MainWindow extends JFrame {
    //todo change signature
    private final StartMenuWidget startMenu = new StartMenuWidget(this);
    private Game game;

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

        setVisible(true);
        setLocationRelativeTo(null);

        try{
            this.game = new Game(new GameField(3,3), "player_one", "player_two");
        }catch (IllegalArgumentException | FileNotFoundException ignored){}
    }

    //fixme
    void initGame(@NotNull Game game){
        this.game = game;
    }

    //todo create func for init Game

    //todo create route to main menu
}

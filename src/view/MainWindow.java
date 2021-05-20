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
//        JPanel contentPane = new JPanel();
//        contentPane.setLayout(new CardLayout());
//        contentPane.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
//
//        contentPane.add(this.startMenu, "Стартовое меню");
//        contentPane.add(this.gameWidget, "Игра");
//
//        setContentPane(contentPane);
//        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void runGame(@NotNull Game game){
        this.gameWidget.setGame(game);
        this.gameWidget.initField();
        this.gameWidget.setVisible(true);
    }
}

package view;

import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    //todo change signature
    private final StartMenuWidget startMenu = new StartMenuWidget();

    public MainWindow(){
        setTitle("Balda. The game");
        setSize(new Dimension(1120, 720));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        setLayout(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //todo add yet windows
        add(this.startMenu, gbc);

        setVisible(true);
        setLocationRelativeTo(null);
    }

    //todo create func for init Game

    //todo create route to main menu
}

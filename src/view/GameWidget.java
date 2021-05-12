package view;

import model.Game;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;
import view.helpers.GridBagHelper;

import javax.swing.*;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private GameFieldWidget gameFieldWidget;
    private final GridBagHelper helper = new GridBagHelper();
    private PlayersWidget playerOne;
    private PlayersWidget playerTwo;


    public GameWidget(MainWindow owner) {
        this.owner = owner;
        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setVisible(false);
    }

    public void initField() {
        setLayout(new GridBagLayout());

        int gridCounter = 0;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0,50,15,50);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.gridy = gridCounter++;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        this.gameFieldWidget = new GameFieldWidget(this);
        add(this.gameFieldWidget, constraints);
        constraints.gridy = gridCounter++;

        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        this.playerOne = new PlayersWidget(this, this.game.getPlayers().get(0));
        add(this.playerOne, constraints);

        constraints.gridx = 1;
        this.playerTwo = new PlayersWidget(this, this.game.getPlayers().get(1));
        add(this.playerTwo, constraints);

        setVisible(true);
    }

    public void setGame(@NotNull Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}

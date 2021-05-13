package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Game;
import model.GameState;
import org.jetbrains.annotations.NotNull;
import view.helpers.CustomActionButton;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private GameFieldWidget gameFieldWidget;
    private PlayersWidget playerOne;
    private PlayersWidget playerTwo;
    private CustomActionButton acceptBtn = new CustomActionButton("подтвердить");
    private CustomActionButton cancelBtn = new CustomActionButton("отменить");
    private AlphabetWidget alphabetWidget;


    public GameWidget(MainWindow owner) {
        this.owner = owner;
        setPreferredSize(new Dimension(1000, 720));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        setVisible(false);
    }

    public void initField() {
        setLayout(new BorderLayout(10,10));
        this.alphabetWidget = new AlphabetWidget(this.owner, this.game.getAlphabet());

        this.gameFieldWidget = new GameFieldWidget(this, new PlayerActionObserver());
        add(this.gameFieldWidget, BorderLayout.CENTER);

        JPanel container = new JPanel(new BorderLayout());
        JPanel playersPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel();
        container.add(playersPanel, BorderLayout.CENTER);
        container.add(controlPanel, BorderLayout.PAGE_END);

        this.playerOne = new PlayersWidget(this, this.game.getPlayers().get(0));
        playersPanel.add(this.playerOne, BorderLayout.LINE_START);

        this.playerTwo = new PlayersWidget(this, this.game.getPlayers().get(1));
        playersPanel.add(this.playerTwo, BorderLayout.LINE_END);

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.LINE_AXIS));
        controlPanel.add(Box.createHorizontalGlue());
        controlPanel.add(this.cancelBtn);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        controlPanel.add(this.acceptBtn);

        add(container, BorderLayout.PAGE_END);

        this.acceptBtn.addActionListener(e -> {
            this.game.updateCurrentState();
            if(this.game.determinateWinner() != null)
                this.endGame();
            if(this.game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER)
                this.alphabetWidget.setVisible(true);
        });
        this.cancelBtn.addActionListener(e -> this.game.revertState());

        setVisible(true);
    }

    public void setGame(@NotNull Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    private void endGame(){
        //todo add modal window
        this.game.endGame();
    }

     class PlayerActionObserver implements PlayerActionFieldListener {

        @Override
        public void playerClickToField(PlayerActionFieldEvent e) {
            if(!game.getField().isAvailableCell(e.getPoint(), game.getCurrentState()))
                // todo show modal window with invalid message
                System.out.print("Not valid!");
            else {
                switch (game.getCurrentState()) {
                    case PLAYER_SELECT_CELL_FOR_INSERT_LETTER -> game.getCurrentPlayer()
                            .selectCellForInsertLetter(e.getPoint());
                    case PLAYER_SELECTING_CHARS -> game.getCurrentPlayer().selectCell(e.getPoint());
                    case PLAYER_SUBMITTED_TURN -> game.getCurrentPlayer().submitTurn();
                }
            }
        }

        @Override
        public void playerClickOnAlphabet(PlayerActionFieldEvent e) {
            if(game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER)
                game.getCurrentPlayer().insertLetterIntoCell(e.getLetter());
        }
    }
}

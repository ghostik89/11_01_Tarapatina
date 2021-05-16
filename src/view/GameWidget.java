package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Game;
import model.GameState;
import org.jetbrains.annotations.NotNull;
import view.helpers.*;

import javax.swing.*;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private PlayersWidget playerOne;
    private PlayersWidget playerTwo;
    private final CustomActionButton acceptBtn = new CustomActionButton("подтвердить");
    private final CustomActionButton changePlayerBtn = new CustomActionButton("пропустить ход");
    private final CustomActionButton cancelBtn = new CustomActionButton("отменить");


    public GameWidget(MainWindow owner) {
        this.owner = owner;
        setPreferredSize(new Dimension(1000, 720));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        setVisible(false);
    }

    public void initField() {
        AlphabetWidget alphabetWidget;
        setLayout(new BorderLayout(10,10));
        alphabetWidget = new AlphabetWidget(this.owner, this.game.getAlphabet());
        PlayerActionObserver observer = new PlayerActionObserver();
        GameFieldWidget gameFieldWidget = new GameFieldWidget(this, observer);
        alphabetWidget.addListener(observer);
        add(gameFieldWidget, BorderLayout.CENTER);

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
        controlPanel.add(this.changePlayerBtn);
        controlPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        controlPanel.add(this.acceptBtn);

        add(container, BorderLayout.PAGE_END);

        this.acceptBtn.addActionListener(e -> {
            this.game.updateCurrentState();

            if (this.game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER) {
                alphabetWidget.setVisible(true);
            } else if (this.game.getCurrentState() == GameState.PLAYER_SUBMITTED_TURN)
                this.submitTurn();
        });
        this.cancelBtn.addActionListener(e -> this.game.revertState());

        setVisible(true);
    }

    private void initModalAddingWords(){
        CustomModal addModal = SnackbarFactory.createBasicInfoSnackbar("Ваше слово не существует в словаре\nВы хотите его добавить?", this.owner);
        CustomActionButton addButton = new CustomActionButton("Добавить");
        String wordToAdd = this.game.getField().getWordSettedAtTurn();
        addButton.addActionListener(e -> {
            this.game.getWordManager().addWordToDictionary(wordToAdd);
            this.submitTurn();
        });
        addModal.addButton(addButton);
        addModal.setVisible(true);
    }

    private void submitTurn(){
        switch (this.game.getCurrentPlayer().submitTurn()){
            case TURN_SUCCESS -> {
                if (this.game.determinateWinner() != null)
                    this.endGame();
                this.game.getField().cleanFieldAfterPlayersTurn();
                this.game.updateCurrentState();
                this.game.changePlayer();
                repaint();
                revalidate();
            }
            case WORDMANAGER_ERROR_IS_SOLVED -> SnackbarFactory
                    .createBasicInfoSnackbar("Ваше слово уже было отгадано", this.owner)
                    .setVisible(true);
            case WORDMANAGER_ERROR_NOT_FOUND -> this.initModalAddingWords();
            case GAMEFIELD_HAS_NOT_LETTER_SET_AT_TURN -> SnackbarFactory
                    .createBasicInfoSnackbar("Среди выделенных клеток нет той новой буквы!", this.owner)
                    .setVisible(true);
        }
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
                SnackbarFactory
                        .createBasicInfoSnackbar("Вы не можете совершить действие с клеткой", owner)
                        .setVisible(true);
            else {
                if (game.getCurrentState() == GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER) {
                    game.getCurrentPlayer()
                            .selectCellForInsertLetter(e.getPoint());
                } else if (game.getCurrentState() == GameState.PLAYER_SELECTING_CHARS) {
                    game.getCurrentPlayer().selectCell(e.getPoint());
                }
            }
            repaint();
            revalidate();
        }

        @Override
        public void playerClickOnAlphabet(PlayerActionFieldEvent e) {
            if(game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER)
                game.getCurrentPlayer().insertLetterIntoCell(e.getLetter());
            repaint();
            revalidate();
        }
    }
}

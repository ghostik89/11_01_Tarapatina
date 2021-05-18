package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.Game;
import model.GameState;
import org.jetbrains.annotations.NotNull;
import view.helpers.*;
import view.helpers.components.CustomActionButton;
import view.helpers.components.CustomModal;
import view.helpers.factories.CustomActionButtonFactory;
import view.helpers.factories.SnackbarFactory;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private PlayersWidget playerOne;
    private PlayersWidget playerTwo;
    private final CustomActionButton acceptBtn = new CustomActionButton("подтвердить");
    private final CustomActionButton changePlayerBtn = new CustomActionButton("пропустить ход");
    private final CustomActionButton cancelBtn = new CustomActionButton("отменить");
    private final JLabel headerHelper = StyledLabelFactory.createCustomLabel("", GlobalStyles.MAIN_HEADER_FONT);


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
        setPreferredSize(new Dimension(1200, 600));

        alphabetWidget = new AlphabetWidget(this.owner, this.game.getAlphabet());
        PlayerActionObserver observer = new PlayerActionObserver();

        JPanel headerLayout = new JPanel();
        headerLayout.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        headerLayout.setLayout(new BorderLayout(10,10));
        headerLayout.add(this.headerHelper, BorderLayout.CENTER);
        CustomActionButton exitBtn = CustomActionButtonFactory.createButtonWithoutBorder("Завершить игру");
        exitBtn.setBorder(BorderFactory.createEmptyBorder());
        exitBtn.addActionListener(e -> this.endGame());
        headerLayout.add(exitBtn, BorderLayout.LINE_END);
        add(headerLayout, BorderLayout.PAGE_START);

        this.playerOne = new PlayersWidget(this, this.game.getPlayers().get(0));
        add(this.playerOne, BorderLayout.LINE_START);

        this.playerTwo = new PlayersWidget(this, this.game.getPlayers().get(1));
        add(this.playerTwo, BorderLayout.LINE_END);

        JPanel gameLayout = new JPanel();
        gameLayout.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        GameFieldWidget gameFieldWidget = new GameFieldWidget(this, observer);
        alphabetWidget.addListener(observer);
        gameLayout.add(gameFieldWidget, BorderLayout.PAGE_START);

        JPanel container = new JPanel(new FlowLayout());

        container.add(this.cancelBtn);
        container.add(this.acceptBtn);
        container.add(this.changePlayerBtn);
        gameLayout.add(container, BorderLayout.PAGE_END);

        add(gameLayout, BorderLayout.CENTER);

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
        CustomModal addModal = SnackbarFactory.createBasicInfoSnackbar(
                "<html>Ваше слово не существует в словаре<br>Вы хотите его добавить?</html>", this.owner);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.game.getCurrentState()){
            case PLAYER_SUBMITTED_TURN -> this.headerHelper.setText("Идет проверка правильности хода...");
            case PLAYER_SELECTING_CHARS -> this.headerHelper.setText("Выберите ячейки, чтобы составить слово");
            case PLAYER_INSERTING_LETTER -> this.headerHelper.setText("Вставка буквы...");
            case PLAYER_SELECT_CELL_FOR_INSERT_LETTER -> this.headerHelper.setText("Выберите ячейку, чтобы вставить букву");
        }
    }

     class PlayerActionObserver implements PlayerActionFieldListener {

        @Override
        public void playerClickToField(PlayerActionFieldEvent e) {
            if(!game.getField().isAvailableCell(e.getPoint(), game.getCurrentState()))
                SnackbarFactory
                        .createBasicInfoSnackbar("Вы не можете совершить действие с ячейкой", owner)
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

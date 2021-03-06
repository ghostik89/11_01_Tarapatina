package view;

import event.PlayerActionFieldEvent;
import event.PlayerActionFieldListener;
import model.*;
import org.jetbrains.annotations.NotNull;
import view.helpers.*;
import view.helpers.components.CustomActionButton;
import view.helpers.components.CustomModal;
import view.helpers.factories.CustomActionButtonFactory;
import view.helpers.factories.DialogFactory;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;

public class GameWidget extends JPanel {
    private Game game;
    private final MainWindow owner;
    private final CustomActionButton acceptBtn = CustomActionButtonFactory.createOutlinedButton("Подтвердить");
    private final CustomActionButton changePlayerBtn = CustomActionButtonFactory
            .createButtonWithoutBorder("пропустить ход");
    private final CustomActionButton cancelBtn = CustomActionButtonFactory.createButtonWithoutBorder("отмена действия");
    private final JLabel headerHelper = StyledLabelFactory.createCustomLabel("", GlobalStyles.MAIN_HEADER_FONT);


    public GameWidget(MainWindow owner) {
        this.owner = owner;
        setPreferredSize(new Dimension(1000, 720));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        setVisible(false);
    }

    public void initField() {
        removeAll();
        revalidate();

        AlphabetWidget alphabetWidget ;
        setLayout(new BorderLayout(10,10));
        setPreferredSize(new Dimension(1200, 620));

        alphabetWidget = new AlphabetWidget(this.owner, this.game.getAlphabet(), this);
        PlayerActionObserver observer = new PlayerActionObserver();

        JPanel headerLayout = new JPanel();
        headerLayout.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        headerLayout.setLayout(new BorderLayout(10,10));
        headerLayout.add(this.headerHelper, BorderLayout.CENTER);
        this.headerHelper.setHorizontalAlignment(SwingConstants.CENTER);
        CustomActionButton exitBtn = CustomActionButtonFactory.createButtonWithoutBorder("Завершить игру");
        exitBtn.setBorder(BorderFactory.createEmptyBorder());
        exitBtn.addActionListener(e -> this.endGame());
        headerLayout.add(exitBtn, BorderLayout.LINE_END);
        add(headerLayout, BorderLayout.PAGE_START);

        PlayersWidget playerOne = new PlayersWidget(this, this.game.getPlayers().get(0));
        add(playerOne, BorderLayout.LINE_START);

        PlayersWidget playerTwo = new PlayersWidget(this, this.game.getPlayers().get(1));
        add(playerTwo, BorderLayout.LINE_END);

        JPanel gameLayout = new JPanel();
        gameLayout.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        GameFieldWidget gameFieldWidget = new GameFieldWidget(this, observer);
        alphabetWidget.addListener(observer);
        gameLayout.add(gameFieldWidget, BorderLayout.PAGE_START);

        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 25,2));
        container.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        container.add(this.cancelBtn);
        container.add(this.acceptBtn);
        this.acceptBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GlobalStyles.BTN_PRIMARY_COLOR, 2),
                BorderFactory.createEmptyBorder(10,60,10,60)));

        container.add(this.changePlayerBtn);
        gameLayout.add(container, BorderLayout.PAGE_END);

        add(gameLayout, BorderLayout.CENTER);

        if(this.acceptBtn.getActionListeners().length == 0) {
            this.acceptBtn.addActionListener(e -> {
                this.game.updateCurrentState();

                if (this.game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER) {
                    alphabetWidget.setVisible(true);
                } else if (this.game.getCurrentState() == GameState.PLAYER_SUBMITTED_TURN) {
                    this.submitTurn();
                }
            });
        }

        if(this.cancelBtn.getActionListeners().length == 0)
            this.cancelBtn.addActionListener(e -> {
                this.game.revertState();
                repaint();
                revalidate();
            });

        if(this.changePlayerBtn.getActionListeners().length == 0)
            this.changePlayerBtn.addActionListener(e -> {
                this.game.changePlayer();
                this.game.revertState();
                repaint();
                revalidate();
            });

        setVisible(true);
    }

    private void initModalAddingWords(){
        CustomModal addModal = new CustomModal(this.owner,
                StyledLabelFactory
                        .createCustomLabel("<html>Ваше слово не существует в словаре<br>Вы хотите его " +
                                "добавить?</html>", GlobalStyles.HEADER_FONT));
        CustomActionButton addButton = CustomActionButtonFactory.createButtonWithoutBorder("да");
        String wordToAdd = this.game.getField().getWordSettedAtTurn();
        addButton.addActionListener(e -> {
            this.game.getWordManager().addWordToDictionary(wordToAdd);
            this.submitTurn();
            addModal.setVisible(false);
        });
        addModal.addButton(addButton);
        CustomActionButton cancelButton = CustomActionButtonFactory.createButtonWithoutBorder("нет");
        cancelButton.addActionListener(e -> {
            addModal.setVisible(false);
            this.game.revertState();
            this.repaint();
        });
        addModal.addButton(cancelButton);

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
                Cell.resetStaticIndex();
                repaint();
                revalidate();
            }
            case WORDMANAGER_ERROR_IS_SOLVED -> DialogFactory
                    .createBasicInfoSnackbar("Ваше слово уже было отгадано", this.owner)
                    .setVisible(true);
            case WORDMANAGER_ERROR_NOT_FOUND -> this.initModalAddingWords();
            case GAMEFIELD_HAS_NOT_LETTER_SET_AT_TURN -> DialogFactory
                    .createBasicInfoSnackbar("<html>Среди выделенных клеток<br> нет той новой буквы!<html>",
                            this.owner).setVisible(true);
        }
        this.game.revertState();
        repaint();
    }

    public void setGame(@NotNull Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    private void endGame(){
        String winner = this.game.determinateWinner() == null ? "Игра еще не закончена."
                : "Выйграл игрок " + this.game.determinateWinner();
        CustomModal endModal = new CustomModal(owner, StyledLabelFactory.createBasicLabel(winner + " Вы уверены, что хотите выйти?"));

        CustomActionButton okBtn = CustomActionButtonFactory.createButtonWithoutBorder("Да");
        okBtn.addActionListener(e -> {
            this.game.endGame();
            this.owner.revertGame();
            endModal.setVisible(false);
        });
        endModal.addButton(okBtn);

        CustomActionButton cancelBtn = CustomActionButtonFactory.createButtonWithoutBorder("Нет");
        cancelBtn.addActionListener(e -> endModal.setVisible(false));
        endModal.addButton(cancelBtn);

        endModal.setVisible(true);
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
        boolean isDisabledAccept = (this.game.getCurrentState() == GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER
                && this.game.getField().getLetterSettedAtTurn() == null) || (this.game.getCurrentState() ==
                GameState.PLAYER_SELECTING_CHARS && this.game.getField().getAllSelectedCells().isEmpty());

        this.cancelBtn.setEnabled(this.game.getCurrentState() != GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER);
        this.acceptBtn.setEnabled(!isDisabledAccept);
    }

     class PlayerActionObserver implements PlayerActionFieldListener {

        @Override
        public void playerClickToField(PlayerActionFieldEvent e) {
            if(!game.getField().isAvailableCell(e.getPoint(), game.getCurrentState()))
                DialogFactory
                        .createBasicInfoSnackbar("Вы не можете совершить действие с ячейкой", owner)
                        .setVisible(true);
            else {
                if (game.getCurrentState() == GameState.PLAYER_SELECT_CELL_FOR_INSERT_LETTER) {
                    game.getCurrentPlayer()
                            .selectCellForInsertLetter(e.getPoint());
                } else if (game.getCurrentState() == GameState.PLAYER_SELECTING_CHARS) {
                    game.getCurrentPlayer().selectCell(e.getPoint());
                }
                repaint();
                revalidate();
            }
        }

        @Override
        public void playerClickOnAlphabet(PlayerActionFieldEvent e) {
            if(!game.getAlphabet().letterHasInAlphabet(e.getLetter())) {
                DialogFactory.createBasicInfoSnackbar("Вы не выбрали букву!", owner).setVisible(true);
                game.revertState();
            }
            else if(game.getCurrentState() == GameState.PLAYER_INSERTING_LETTER) {
                game.getCurrentPlayer().insertLetterIntoCell(e.getLetter());
                game.updateCurrentState();
            }
            repaint();
            revalidate();
        }
    }
}

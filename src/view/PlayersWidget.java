package view;
import model.Player;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayersWidget extends JPanel {
    private final GameWidget owner;
    private final Player player;
    private final JLabel playersScore = StyledLabelFactory.createCustomLabel("", GlobalStyles.HEADER_FONT);
    private final JPanel wordsPanel = new JPanel();
    private final JLabel wordsList = StyledLabelFactory.createBasicLabel("");
    private JLabel playersName = new JLabel();
    private JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);


    public PlayersWidget(@NotNull GameWidget owner, @NotNull Player player) {
        this.owner = owner;
        this.player = player;
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new BorderLayout(20,20));
        playersName = StyledLabelFactory.createCustomLabel(this.player.getName(), GlobalStyles.HEADER_FONT);

        setPreferredSize(new Dimension(160,511));
        JPanel namePanel = new JPanel();
        namePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        JPanel rowLayout = new JPanel(new BorderLayout(20, 20));
        rowLayout.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
        wordsPanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        wordsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        wordsPanel.add(this.wordsList);

        rowLayout.add(namePanel, BorderLayout.PAGE_START);
        rowLayout.add(scorePanel, BorderLayout.PAGE_END);

        rowLayout.setPreferredSize(new Dimension(210,80));

        add(rowLayout, BorderLayout.PAGE_START);
        add(wordsPanel, BorderLayout.CENTER);

        namePanel.add(playersName, BorderLayout.PAGE_START);
        scorePanel.add(this.playersScore, BorderLayout.PAGE_START);
        scorePanel.add(this.divider, BorderLayout.PAGE_END);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.playersScore.setText("Счет: " + this.player.calcScore());
        wordsList.setText("<html>");

        ArrayList<String> playersWords = new ArrayList<>(this.owner.getGame().getWordManager()
                .getAllSolvedWordsByPlayer(this.player));
        playersWords.forEach(word -> wordsList.setText(wordsList.getText() + word + "<br>"));

        wordsList.setText(wordsList.getText() + "<html>");

        if(this.player == this.owner.getGame().getCurrentPlayer()) {
            playersName.setForeground(GlobalStyles.SELECTED_CELL);
            setBorder(BorderFactory.createLineBorder(GlobalStyles.SELECTED_CELL, 1));
            divider.setBackground(GlobalStyles.SELECTED_CELL);
        }
        else {
            playersName.setForeground(GlobalStyles.PRIMARY_TEXT_COLOR);
            divider.setBackground(GlobalStyles.CELL_BORDER_COLOR);
            setBorder(BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR));
        }
    }
}

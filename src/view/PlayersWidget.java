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

    public PlayersWidget(@NotNull GameWidget owner, @NotNull Player player) {
        this.owner = owner;
        this.player = player;
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new BorderLayout(20,20));

        setPreferredSize(new Dimension(160,511));
        JPanel namePanel = new JPanel();
        namePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        JPanel rowLayout = new JPanel(new BorderLayout(20, 20));
        wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
        wordsPanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        rowLayout.add(namePanel, BorderLayout.PAGE_START);
        rowLayout.add(scorePanel, BorderLayout.PAGE_END);

        rowLayout.setPreferredSize(new Dimension(210,80));

        add(rowLayout, BorderLayout.PAGE_START);
        add(wordsPanel, BorderLayout.PAGE_END);

        JLabel playersName = StyledLabelFactory.createCustomLabel(this.player.getName(), GlobalStyles.HEADER_FONT);
        namePanel.add(playersName, BorderLayout.PAGE_START);
        scorePanel.add(this.playersScore, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.playersScore.setText("Счет: " + this.player.calcScore());
        wordsPanel.removeAll();
        ArrayList<String> playersWords = new ArrayList<>(this.owner.getGame().getWordManager()
                .getAllSolvedWordsByPlayer(this.player));
        playersWords.forEach(word -> wordsPanel.add(StyledLabelFactory.createBasicLabel(word)));

        if(this.player == this.owner.getGame().getCurrentPlayer())
            setBorder(BorderFactory.createLineBorder(GlobalStyles.SELECTED_CELL, 1));
        else
            setBorder(BorderFactory.createLineBorder(GlobalStyles.FIELD_BORDER_COLOR));
    }
}

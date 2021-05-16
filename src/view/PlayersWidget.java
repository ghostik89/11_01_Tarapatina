package view;
import model.Player;
import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;
import view.helpers.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayersWidget extends JPanel {
    private final GameWidget owner;
    private final Player player;
    private final JLabel playersScore = new JLabel();
    private final JPanel wordsPanel = new JPanel();

    public PlayersWidget(@NotNull GameWidget owner, @NotNull Player player) {
        this.owner = owner;
        this.player = player;
        setLayout(new BorderLayout(20,20));

        setPreferredSize(new Dimension(100,100));
        JPanel namePanel = new JPanel();
        namePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);

        JPanel rowLayout = new JPanel(new BorderLayout(20, 20));
        wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
        wordsPanel.setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        namePanel.setPreferredSize(new Dimension(150,60));
        scorePanel.setPreferredSize(new Dimension(50, 60));

        rowLayout.add(namePanel, BorderLayout.LINE_START);
        rowLayout.add(scorePanel, BorderLayout.LINE_END);

        rowLayout.setPreferredSize(new Dimension(210,80));

        add(rowLayout, BorderLayout.PAGE_START);
        add(wordsPanel, BorderLayout.PAGE_END);

        JLabel playersName = new JLabel();
        namePanel.add(playersName, BorderLayout.SOUTH);
        scorePanel.add(this.playersScore, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.playersScore.setText(String.valueOf(this.player.calcScore()));
        ArrayList<String> playersWords = new ArrayList<>(this.owner.getGame().getWordManager()
                .getAllSolvedWordsByPlayer(this.player));
        playersWords.forEach(word -> wordsPanel.add(StyledLabelFactory.createBasicLabel(word)));
    }
}

package view;
import model.Player;
import org.jetbrains.annotations.NotNull;
import view.helpers.CustomLabel;
import view.helpers.GridBagHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayersWidget extends JPanel {
    private final GameWidget owner;
    private final Player player;
    private final CustomLabel playersName = new CustomLabel();
    private final ArrayList<CustomLabel> playersWords = new ArrayList<>();
    private final GridBagHelper helper = new GridBagHelper();

    public PlayersWidget(@NotNull GameWidget owner, @NotNull Player player) {
        this.owner = owner;
        this.player = player;

        setPreferredSize(new Dimension(100,100));
        setLayout(new GridBagLayout());
        this.playersName.setText(this.player.getName());
        add(this.playersName, helper.get());
        helper.nextRow();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        playersWords.forEach(jLabel -> {
            add(jLabel, helper.get());
            helper.nextRow();
        });
    }
}

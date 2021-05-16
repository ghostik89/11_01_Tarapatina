package view.helpers.factories;

import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;

import javax.swing.*;
import java.awt.*;

public class StyledLabelFactory {
    public static JLabel createBasicLabel(@NotNull String text){
        JLabel label = new JLabel(text);
        label.setFont(GlobalStyles.MAIN_FONT);

        return label;
    }

    public static JLabel createCustomLabel(@NotNull String text, @NotNull Font font){
        JLabel label = new JLabel(text);
        label.setFont(font);

        return label;
    }
}

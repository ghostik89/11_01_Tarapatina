package view.helpers;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(@NotNull String text, @NotNull Font font) {
        super(text);
        setFont(font);
    }

    public CustomLabel(@NotNull String text){
        super(text);
        setFont(GlobalStyles.MAIN_FONT);
    }

    public CustomLabel() {
        super();
    }
}

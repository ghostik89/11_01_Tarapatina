package view.helpers.factories;

import org.jetbrains.annotations.NotNull;
import view.helpers.components.CustomActionButton;
import view.helpers.GlobalStyles;

import javax.swing.*;

public class CustomActionButtonFactory{
    public static CustomActionButton createOutlinedButton(@NotNull String text){
        CustomActionButton btn = new CustomActionButton(text);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GlobalStyles.BTN_PRIMARY_COLOR, 2),
                BorderFactory.createEmptyBorder(10,0,10,0)));
        btn.setFont(GlobalStyles.MAIN_BTN_FONT);
        return btn;
    }

    public static CustomActionButton createButtonWithoutBorder(@NotNull String text){
        CustomActionButton btn = new CustomActionButton(text);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setFont(GlobalStyles.MAIN_BTN_FONT);
        return btn;
    }
}

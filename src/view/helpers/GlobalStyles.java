package view.helpers;

import java.awt.*;

public interface GlobalStyles {
    String fontName = "Roboto";
    //cell colors
    Color CELL_BORDER_COLOR = Color.decode("#9EB0BE");
    Color SELECTED_CELL = Color.decode("#0077D9");
    Color CELL_BLOCKED = Color.red;
    Color INSERTED_LETTER = Color.decode("#9A9898");
    Color INSERTED_BACKGROUND = Color.decode("#4399DF");

    //text colors
    Color PRIMARY_TEXT_COLOR = Color.black;
    Color SECONDARY_TEXT_COLOR = Color.white;

    //background colors
    Color PRIMARY_BACKGROUND_COLOR = Color.white;

    //btn colors
    Color BTN_PRIMARY_COLOR = Color.decode("#005DA8");
    Color BTN_HOVER_COLOR = Color.decode("#E2E2E2");

    //text field colors
    Color FIELD_BORDER_COLOR = Color.decode("#9EB0BE");
    Color FIELD_BORDER_FOCUS_COLOR = Color.decode("#0077D9");


    //fonts
    Font MAIN_HEADER_FONT = new Font(fontName, Font.PLAIN, 30);
    Font HEADER_FONT = new Font(fontName, Font.PLAIN, 20);
    Font MAIN_BTN_FONT = new Font(fontName, Font.PLAIN, 22);
    Font MAIN_FONT = new Font(fontName, Font.PLAIN, 16);
}

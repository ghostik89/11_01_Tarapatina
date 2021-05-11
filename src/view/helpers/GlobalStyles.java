package view.helpers;

import java.awt.*;

public interface GlobalStyles {
    String fontName = "Roboto";
    Color PRIMARY_COLOR = Color.black;
    Color PRIMARY_BACKGROUND_COLOR = Color.white;
    Color BORDER_COLOR = Color.decode("#646464");
    Color SELECTED_CELL = Color.decode("#656769");
    Color INSERTED_LETTER = Color.decode("#9A9898");
    Color INSERTED_BACKGROUND = Color.decode("#CFCFCF");
    Font MAIN_HEADER_FONT = new Font(fontName, Font.BOLD, 28);
    Font HEADER_FONT = new Font(fontName, Font.PLAIN, 20);
    Font MAIN_FONT = new Font(fontName, Font.PLAIN, 16);
}

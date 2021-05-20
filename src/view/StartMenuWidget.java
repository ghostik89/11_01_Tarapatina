package view;
import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.*;
import view.helpers.components.CustomActionButton;
import view.helpers.components.TextInput;
import view.helpers.factories.CustomActionButtonFactory;
import view.helpers.factories.DialogFactory;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StartMenuWidget extends JPanel{
    private final MainWindow owner;
    private final TextInput firstName = new TextInput();
    private final TextInput secondName = new TextInput();
    private final JSpinner widthForm = new JSpinner(new SpinnerNumberModel(3, 3, 15, 1));
    private final JSpinner heightForm = new JSpinner(new SpinnerNumberModel(3, 3, 15, 1));

    public StartMenuWidget(@NotNull MainWindow owner){
        super();
        this.owner = owner;

        // init grid
        int gridCounter = 0;
        setPreferredSize(new Dimension(600, 600));
        setBackground(GlobalStyles.PRIMARY_BACKGROUND_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(15,50,15,50);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.gridy = gridCounter++;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.CENTER;

        this.widthForm.setFont(GlobalStyles.MAIN_FONT);
        this.heightForm.setFont(GlobalStyles.MAIN_FONT);

        //main header
        constraints.gridwidth = 2;
        add(StyledLabelFactory.createCustomLabel("Игра балда. Новая игра",
                GlobalStyles.MAIN_HEADER_FONT), constraints);
        constraints.gridy = gridCounter++;

        JSeparator divider1 = new JSeparator(SwingConstants.HORIZONTAL);
        divider1.setBackground(GlobalStyles.CELL_BORDER_COLOR);
        divider1.setBorder(BorderFactory.createLineBorder(GlobalStyles.CELL_BORDER_COLOR, 1));
        add(divider1, constraints);
        constraints.gridy = gridCounter++;

        //dimension form header
        constraints.gridwidth = 2;
        add(StyledLabelFactory.createCustomLabel("Размер поля", GlobalStyles.HEADER_FONT), constraints);
        constraints.gridy = gridCounter++;

        //dimension form width
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        add(StyledLabelFactory.createBasicLabel("ширина:"), constraints);
        constraints.gridx = 1;
        add(this.widthForm, constraints);
        constraints.gridy = gridCounter++;

        //dimension form height
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        add(StyledLabelFactory.createBasicLabel("высота:"), constraints);
        constraints.gridx = 1;
        add(this.heightForm, constraints);
        constraints.gridy = gridCounter++;

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        JSeparator divider2 = new JSeparator(SwingConstants.HORIZONTAL);
        divider2.setBackground(GlobalStyles.CELL_BORDER_COLOR);
        divider2.setBorder(BorderFactory.createLineBorder(GlobalStyles.CELL_BORDER_COLOR, 1));
        add(divider2, constraints);
        constraints.gridy = gridCounter++;


        //names form header
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = gridCounter++;
        add(StyledLabelFactory.createCustomLabel("Имена игроков", GlobalStyles.HEADER_FONT), constraints);
        constraints.gridy = gridCounter++;

        //first name form
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        add(StyledLabelFactory.createBasicLabel("первый игрок:"), constraints);
        constraints.gridx = 1;
        add(this.firstName, constraints);
        constraints.gridy = gridCounter++;

        //second name form
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        add(StyledLabelFactory.createBasicLabel("второй игрок:"), constraints);
        constraints.gridx = 1;
        add(this.secondName, constraints);
        constraints.gridy = gridCounter++;

        //button
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = gridCounter;

        CustomActionButton startBtn = CustomActionButtonFactory.createOutlinedButton("Новая игра");
        startBtn.addActionListener(e -> this.handleStart());
        add(startBtn, constraints);

        setVisible(true);
    }

    private void handleStart(){
        try{
            GameField field = new GameField((Integer) this.widthForm.getValue(), (Integer) this.heightForm.getValue());
            this.owner.runGame(new Game(field, this.firstName.getText(), this.secondName.getText()));
            setVisible(false);
        }catch (IllegalArgumentException arg){
            DialogFactory.createBasicInfoSnackbar("Ошибка! " + arg.getMessage() + "!", this.owner)
            .setVisible(true);
        }catch (FileNotFoundException notFoundException){
            DialogFactory.createBasicInfoSnackbar("Файл со словарем не найден. Проверьте целостность программы",
                    this.owner).setVisible(true);
        }
    }
}

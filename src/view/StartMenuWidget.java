package view;
import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.*;
import view.helpers.components.CustomActionButton;
import view.helpers.components.CustomModal;
import view.helpers.components.TextInput;
import view.helpers.factories.CustomActionButtonFactory;
import view.helpers.factories.SnackbarFactory;
import view.helpers.factories.StyledLabelFactory;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StartMenuWidget extends JPanel{
    private final MainWindow owner;
    private final TextInput firstName = new TextInput();
    private final TextInput secondName = new TextInput();
    private final JSpinner widthForm = new JSpinner(new SpinnerNumberModel(3, 3, 29, 1));
    private final JSpinner heightForm = new JSpinner(new SpinnerNumberModel(3, 3, 29, 1));

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

        //init font for fields todo refactor to overload classes
        this.widthForm.setFont(GlobalStyles.MAIN_FONT);
        this.heightForm.setFont(GlobalStyles.MAIN_FONT);

        //main header
        constraints.gridwidth = 2;
        JLabel mainHeader = StyledLabelFactory.createCustomLabel("Игра балда. Новая игра",
                GlobalStyles.MAIN_HEADER_FONT);
        add(mainHeader, constraints);
        constraints.gridy = gridCounter++;
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        divider.setBackground(GlobalStyles.PRIMARY_TEXT_COLOR);
        add(divider, constraints);
        constraints.gridy = gridCounter++;

        //dimension form header
        constraints.gridwidth = 2;
        JLabel dimensionHeader = StyledLabelFactory.createBasicLabel("Размер поля");
        dimensionHeader.setFont(GlobalStyles.HEADER_FONT);
        add(dimensionHeader, constraints);
        constraints.gridy = gridCounter++;

        //dimension form width
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        JLabel widthLabel = StyledLabelFactory.createBasicLabel("ширина:");
        add(widthLabel, constraints);
        constraints.gridx = 1;
        add(this.widthForm, constraints);
        constraints.gridy = gridCounter++;

        //dimension form height
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        JLabel heightLabel = StyledLabelFactory.createBasicLabel("высота:");
        add(heightLabel, constraints);
        constraints.gridx = 1;
        add(this.heightForm, constraints);
        constraints.gridy = gridCounter++;
        add(divider, constraints);
        constraints.gridy = gridCounter++;


        //names form header
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        add(divider, constraints);
        constraints.gridy = gridCounter++;
        JLabel namesHeader = StyledLabelFactory.createCustomLabel("Имена игроков", GlobalStyles.HEADER_FONT);
        add(namesHeader, constraints);
        constraints.gridy = gridCounter++;

        //first name form
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        JLabel firstPlayerLabel = StyledLabelFactory.createBasicLabel("первый игрок:");
        add(firstPlayerLabel, constraints);
        constraints.gridx = 1;
        add(this.firstName, constraints);
        constraints.gridy = gridCounter++;

        //second name form
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        JLabel secondPlayerLabel = StyledLabelFactory.createBasicLabel("второй игрок:");
        add(secondPlayerLabel, constraints);
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
            SnackbarFactory.createBasicInfoSnackbar("Неверно введены параметры.", this.owner)
            .setVisible(true);
        }catch (FileNotFoundException notFoundException){
            SnackbarFactory.createBasicInfoSnackbar("Файл со словарем не найден. Проверьте целостность программы",
                    this.owner).setVisible(true);
        }
    }
}

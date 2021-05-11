package view;
import model.Game;
import model.GameField;
import org.jetbrains.annotations.NotNull;
import view.helpers.CustomActionButton;
import view.helpers.CustomLabel;
import view.helpers.CustomModal;
import view.helpers.GlobalStyles;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class StartMenuWidget extends JPanel{
    private final MainWindow owner;
    private final JTextField firstName = new JTextField();
    private final JTextField secondName = new JTextField();
    private final JSpinner widthForm = new JSpinner(new SpinnerNumberModel(3, 3, 29, 1));
    private final JSpinner heightForm = new JSpinner(new SpinnerNumberModel(3, 3, 29, 1));
    private final CustomModal illegalArgumentModal;
    private final CustomModal fileNotFound;

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
        this.firstName.setFont(GlobalStyles.MAIN_FONT);
        this.secondName.setFont(GlobalStyles.MAIN_FONT);

        //main header
        constraints.gridwidth = 2;
        CustomLabel mainHeader = new CustomLabel("Игра балда. Новая игра", GlobalStyles.MAIN_HEADER_FONT);
        add(mainHeader, constraints);
        constraints.gridy = gridCounter++;
        JSeparator divider = new JSeparator(SwingConstants.HORIZONTAL);
        divider.setBackground(GlobalStyles.PRIMARY_COLOR);
        add(divider, constraints);
        constraints.gridy = gridCounter++;

        //dimension form header
        constraints.gridwidth = 2;
        CustomLabel dimensionHeader = new CustomLabel("Размер поля");
        dimensionHeader.setFont(GlobalStyles.HEADER_FONT);
        add(dimensionHeader, constraints);
        constraints.gridy = gridCounter++;

        //dimension form width
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        CustomLabel widthLabel = new CustomLabel("ширина:");
        add(widthLabel, constraints);
        constraints.gridx = 1;
        add(this.widthForm, constraints);
        constraints.gridy = gridCounter++;

        //dimension form height
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        CustomLabel heightLabel = new CustomLabel("высота:");
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
        CustomLabel namesHeader = new CustomLabel("Имена игроков", GlobalStyles.HEADER_FONT);
        add(namesHeader, constraints);
        constraints.gridy = gridCounter++;

        //first name form
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridx = 0;
        CustomLabel firstPlayerLabel = new CustomLabel("первый игрок:");
        add(firstPlayerLabel, constraints);
        constraints.gridx = 1;
        add(this.firstName, constraints);
        constraints.gridy = gridCounter++;

        //second name form
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        CustomLabel secondPlayerLabel = new CustomLabel("второй игрок:");
        add(secondPlayerLabel, constraints);
        constraints.gridx = 1;
        add(this.secondName, constraints);
        constraints.gridy = gridCounter++;

        //button
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = gridCounter;

        CustomActionButton startBtn = new CustomActionButton("НОВАЯ ИГРА");
        startBtn.addActionListener(e -> this.handleStart());
        add(startBtn, constraints);

        setVisible(true);

        //create modal window for illegal argument
        CustomLabel illegalArgumentText = new CustomLabel("Введите верные данные");
        illegalArgumentText.setFont(GlobalStyles.MAIN_FONT);
        this.illegalArgumentModal = new CustomModal(this.owner, illegalArgumentText);
        CustomActionButton cancelBtn1 = new CustomActionButton("ОК");
        cancelBtn1.addActionListener(e -> illegalArgumentModal.setVisible(false));
        this.illegalArgumentModal.addButton(cancelBtn1);

        //crate modal window for file not found
        CustomLabel fileNotFoundText = new CustomLabel("Файл с словарем не найден. Проверьте файлы");
        this.fileNotFound = new CustomModal(this.owner, fileNotFoundText);
        CustomActionButton cancelBtn2 = new CustomActionButton("ОК");
        cancelBtn2.addActionListener(e -> fileNotFound.setVisible(false));
        this.fileNotFound.addButton(cancelBtn2);
    }

    private void handleStart(){
        try{
            GameField field = new GameField((Integer) this.widthForm.getValue(), (Integer) this.heightForm.getValue());
            this.owner.runGame(new Game(field, this.firstName.getText(), this.secondName.getText()));
            setVisible(false);
        }catch (IllegalArgumentException arg){
            this.illegalArgumentModal.setVisible(true);
        }catch (FileNotFoundException notFoundException){
            this.fileNotFound.setVisible(true);
        }
    }
}

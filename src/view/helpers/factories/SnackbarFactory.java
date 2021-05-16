package view.helpers.factories;

import org.jetbrains.annotations.NotNull;
import view.helpers.components.CustomActionButton;
import view.helpers.components.CustomModal;

import javax.swing.*;

public class SnackbarFactory {
    public static CustomModal createBasicInfoSnackbar(@NotNull String message, @NotNull JFrame owner){
        JLabel infoText = StyledLabelFactory.createBasicLabel(message);
        CustomModal infoModal = new CustomModal(owner, infoText);
        CustomActionButton okBtn = CustomActionButtonFactory.createButtonWithoutBorder("ок");
        okBtn.addActionListener(e -> infoModal.setVisible(false));
        infoModal.addButton(okBtn);
        return infoModal;
    }
}

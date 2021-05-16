package view.helpers;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SnackbarFactory {
    public static CustomModal createBasicInfoSnackbar(@NotNull String message, @NotNull JFrame owner){
        JLabel infoText = StyledLabelFactory.createBasicLabel(message);
        CustomModal infoModal = new CustomModal(owner, infoText);
        CustomActionButton okBtn = new CustomActionButton("ОК");
        okBtn.addActionListener(e -> okBtn.setVisible(false));
        infoModal.addButton(okBtn);
        return infoModal;
    }
}

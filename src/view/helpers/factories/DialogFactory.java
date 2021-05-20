package view.helpers.factories;

import org.jetbrains.annotations.NotNull;
import view.helpers.GlobalStyles;
import view.helpers.components.CustomActionButton;
import view.helpers.components.CustomModal;

import javax.swing.*;

public class DialogFactory {
    public static CustomModal createBasicInfoSnackbar(@NotNull String message, @NotNull JFrame owner){
        CustomModal infoModal = new CustomModal(owner,  StyledLabelFactory.createCustomLabel(message, GlobalStyles.HEADER_FONT));
        CustomActionButton okBtn = CustomActionButtonFactory.createButtonWithoutBorder("ок");
        okBtn.addActionListener(e -> infoModal.setVisible(false));
        infoModal.addButton(okBtn);
        return infoModal;
    }
}

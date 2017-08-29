package exe1.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author dudma
 */
public class Alerta {

    public static void mostraErro(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.setTitle("Erro");
        alert.setHeaderText("Erro");
        alert.setContentText(msg);

//		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons()
//				.add(new Image("/br/view/media/icon.png"));
        alert.showAndWait();
    }

    public static void mostraInformacao(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);

//		((Stage) alert.getDialogPane().getScene().getWindow()).getIcons()
//				.add(new Image("/br/view/media/icon.png"));
        alert.showAndWait();
    }
}

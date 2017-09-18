package exe1.controller;

import exe1.App;
import exe1.model.Professor;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import exe1.util.Alerta;
import exe1.persistence.Arquivo;
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author dudma
 */
public class PrincipalController implements Initializable {

    @FXML
    TableView tblListar;

    @FXML
    TableColumn colMatricula;

    @FXML
    TableColumn colNome;

    @FXML
    TableColumn colDepartamento;

    @FXML
    TableColumn colTitulacao;

    @FXML
    TableColumn colRegime;

    @FXML
    TableColumn colExpDocencia;

    @FXML
    TableColumn colExpProfissional;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colMatricula.setCellValueFactory(
                new PropertyValueFactory<>("matricula")
        );

        colNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colDepartamento.setCellValueFactory(
                new PropertyValueFactory<>("departamento")
        );

        colTitulacao.setCellValueFactory(
                new PropertyValueFactory<>("titulacao")
        );

        colRegime.setCellValueFactory(
                new PropertyValueFactory<>("regime")
        );

        colExpDocencia.setCellValueFactory(
                new PropertyValueFactory<>("exp_docencia")
        );

        colExpProfissional.setCellValueFactory(
                new PropertyValueFactory<>("exp_profissional")
        );
    }

    @FXML
    private void btnProcurarArquivo_onAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Arquivo");

        try {
            File arq = fileChooser.showOpenDialog(App.getStage());
            if (arq == null) {
                return;
            }

            ArrayList<Professor> lista = Arquivo.getProfessores(arq);

            ObservableList<Professor> dados = FXCollections.observableArrayList();
            dados.addAll(lista);

            tblListar.setItems(dados);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Alerta.mostraErro("Arquivo não encontrado.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Alerta.mostraErro("Erro inesperado ao ler o arquivo: " + ex.getMessage());
        }
    }
}

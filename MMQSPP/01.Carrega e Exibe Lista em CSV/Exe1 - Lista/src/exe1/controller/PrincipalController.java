package exe1.controller;

import exe1.App;
import exe1.model.Professor;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import exe1.util.Alerta;
import exe1.persistence.Arquivo;

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

    @FXML
    ComboBox<ComboOption> cmbEstrutura;

    @FXML
    ComboBox<ComboOption> cmbPropriedade;

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
            
            List<Professor> lista = Arquivo.getProfessores(arq);
            Iterator<Professor> it = lista.iterator();

            ObservableList<Professor> dados = FXCollections.observableArrayList();
            while (it.hasNext()) {
                dados.add(it.next());
            }

            tblListar.setItems(dados);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            Alerta.mostraErro("Arquivo não encontrado.");
        } catch (Exception ex) {
            ex.printStackTrace();
            Alerta.mostraErro("Erro inesperado ao ler o arquivo: " + ex.getMessage());
        }
    }

    private static class ComboOption {

        int id;
        String descricao;

        public ComboOption(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;
        }

//        public static List<ComboOption> getEstruturas() {
//            List<ComboOption> retorno = new ArrayList();
//
//            retorno.add(new ComboOption(0, "List (LinkedList)"));
//            retorno.add(new ComboOption(1, "ArrayList"));
//            retorno.add(new ComboOption(2, "Árvore (TreeSet)"));
//            retorno.add(new ComboOption(3, "Pilha"));
//            retorno.add(new ComboOption(4, "Fila"));
//
//            return retorno;
//        }

//        public static List<ComboOption> getPropriedades() {
//            List<ComboOption> retorno = new ArrayList();
//
//            retorno.add(new ComboOption(0, "Matrícula"));
//            retorno.add(new ComboOption(1, "Curso"));
//            retorno.add(new ComboOption(2, "Nome"));
////            retorno.add(new ComboOption(3, "Disciplinas"));
//
//            return retorno;
//        }

        @Override
        public String toString() {
            return descricao;
        }
    }
}

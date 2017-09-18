package br.com.eduardo.templatemethod.controller;

import br.com.eduardo.templatemethod.App;
import br.com.eduardo.templatemethod.model.Professor;
import java.io.File;
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
import br.com.eduardo.templatemethod.util.Alerta;
import br.com.eduardo.templatemethod.persistence.*;
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

        cmbPropriedade.getItems().addAll(ComboOption.getPropriedades());
        cmbPropriedade.setValue(cmbPropriedade.getItems().get(0));
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
            
            PProfessor pprofessor = null;
            switch (cmbPropriedade.getValue().id) {
                case 0: // Matrícula
                    pprofessor = new PProfessorOrdMatricula(arq);
                    break;
                case 1: // Nome
                    pprofessor = new PProfessorOrdNome(arq);
                    break;
                case 2: // Departamento, Nome
                    pprofessor = new PProfessorOrdDepartamentoNome(arq);
                    break;
                case 3: // Titulação, Nome
                    pprofessor = new PProfessorOrdTitulacaoNome(arq);
                    break;
                case 4: // Regime, Nome
                    pprofessor = new PProfessorOrdRegimeNome(arq);
                    break;
                case 5: // Departamento, Titulação, Nome
                    pprofessor = new PProfessorOrdDepartamentoTitulacaoNome(arq);
                    break;
                case 6: // Tempo de Docência, Titulação, Matrícula
                    pprofessor = new PProfessorOrdDocenciaTitulacaoMatricula(arq);
                    break;
                case 7: // Regime, Titulação, Nome
                    pprofessor = new PProfessorOrdRegimeTitulacaoNome(arq);
                    break;
            }

            Iterator<Professor> it = pprofessor.listarProfessores();

            ObservableList<Professor> dados = FXCollections.observableArrayList();
            while (it.hasNext()) {
                dados.add(it.next());
            }

            tblListar.setItems(dados);
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
        public static List<ComboOption> getPropriedades() {
            List<ComboOption> retorno = new ArrayList();

            retorno.add(new ComboOption(0, "Matrícula"));
            retorno.add(new ComboOption(1, "Nome"));
            retorno.add(new ComboOption(2, "Departamento, Nome"));
            retorno.add(new ComboOption(3, "Titulação, Nome"));
            retorno.add(new ComboOption(4, "Regime, Nome"));
            retorno.add(new ComboOption(5, "Departamento, Titulação, Nome"));
            retorno.add(new ComboOption(6, "Tempo de Docência, Titulação, Matrícula"));
            retorno.add(new ComboOption(7, "Regime, Titulação, Nome"));
            
            return retorno;
        }

        @Override
        public String toString() {
            return descricao;
        }
    }
}

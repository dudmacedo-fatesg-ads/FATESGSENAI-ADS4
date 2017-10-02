package br.com.eduardo.ads4.mmqspp.adapter;

/**
 *
 * @author eduardo
 */
import br.com.eduardo.ads4.mmqspp.adapter.model.AnimalAdapter;
import br.com.eduardo.ads4.mmqspp.adapter.model.Cachorro;
import br.com.eduardo.ads4.mmqspp.adapter.model.CachorroGatoAdapter;
import br.com.eduardo.ads4.mmqspp.adapter.model.Gato;
import br.com.eduardo.ads4.mmqspp.adapter.model.Vaca;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.Aluno;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.AlunoAdapter;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.AlunoLegado;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.Disciplina;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.Matricula;
import br.com.eduardo.ads4.mmqspp.adapter.model.aluno.IAluno;

/**
 *
 * @author eduardo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        AnimalAdapter animal = new AnimalAdapter(new Cachorro());
//        animal.fazBarulho();
//        
//        Cachorro cao = new CachorroGatoAdapter(new Gato());
//        cao.latir();
        
        AlunoLegado al = new AlunoLegado(
                "2004.1.033.0066", "ANTONIO NEIVA COELHO JUNIOR", 
                "CMP1110-A01  CMP1160-A01  CMP1170-A01  ENG4281-A01  MAF1570-A04".split("  "));
        
        System.out.println(al.getMatricula() + " - " + al.getNome() + " - " + String.join(", ", al.getDisciplinas()));
        
        IAluno aluno = new Aluno(
                new Matricula("2004.1.033.0066"),
                "ANTONIO NEIVA COELHO JUNIOR", 
                Disciplina.getDisciplinas("CMP1110-A01  CMP1160-A01  CMP1170-A01  ENG4281-A01  MAF1570-A04"));
        
        System.out.println(aluno.getMatricula() + " - " + aluno.getNome() + " - " + aluno.getCurso() + " - " + aluno.getDisciplinas().toString());
        
        aluno = new AlunoAdapter(al);
        
        System.out.println(aluno.getMatricula() + " - " + aluno.getNome() + " - " + aluno.getCurso() + " - " + aluno.getDisciplinas().toString());
    }
    
}

package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

/**
 *
 * @author eduardo
 */
public class AlunoLegado {
    private String matricula;
    private String nome;
    private String[] disciplinas;
    
    public AlunoLegado(String matricula, String nome, String[] disciplinas) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplinas = disciplinas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String[] getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(String[] disciplinas) {
        this.disciplinas = disciplinas;
    }
}

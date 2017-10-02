package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

import java.util.List;

/**
 *
 * @author eduardo
 */
public class Aluno implements IAluno {
    private Matricula matricula;
    private String nome;
    private List<Disciplina> disciplinas;
    
    public Aluno (Matricula matricula, String nome, List<Disciplina> disciplinas) {
        this.matricula = matricula;
        this.nome = nome;
        this.disciplinas = disciplinas;
    }
    
    @Override
    public Matricula getMatricula() {
        return matricula;
    }
    
    @Override
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
    
    @Override
    public String getCurso() {
//        028 = Ciência da Computação
//        033 = Engenharia da Computação
//        048 = Engenharia de Software
        switch (matricula.getCurso()) {
            case 28:
                return "Ciência da Computação";
            case 33:
                return "Engenharia da Computação";
            case 48:
                return "Engenharia de Software";
            default:
                return "Não definido";
        }
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    @Override
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}

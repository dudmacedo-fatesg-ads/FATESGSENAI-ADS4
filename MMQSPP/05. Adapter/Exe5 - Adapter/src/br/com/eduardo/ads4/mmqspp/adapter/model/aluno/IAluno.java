package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

import java.util.List;

/**
 *
 * @author eduardo
 */
public interface IAluno {
    public Matricula getMatricula();
    
    public void setMatricula(Matricula matricula);
    
    public String getCurso();
    
    public String getNome();
    
    public void setNome(String nome);
    
    public List<Disciplina> getDisciplinas();
    
    public void setDisciplinas(List<Disciplina> disciplinas);
}

package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

import java.util.ArrayList;

/**
 *
 * @author eduardo
 */
public class Disciplina {
    private String codigo;
    private String turma;
    
    public Disciplina(String disciplina) {
        try{
        String[] dados = disciplina.split("-");
        codigo = dados[0];
        turma = dados[1];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(disciplina);
        }
    }
    public Disciplina(String codigo, String turma) {
        this.codigo = codigo;
        this.turma = turma;
    }
    
    public static ArrayList<Disciplina> getDisciplinas(String lista) {
        ArrayList retorno = new ArrayList<>();
        
        String[] dados = lista.split("  ");
        for (String d : dados) {
            retorno.add(new Disciplina(d));
        }
        
        return retorno;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the turma
     */
    public String getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(String turma) {
        this.turma = turma;
    }
    
    @Override
    public String toString() {
        return codigo + "-" + turma;
    }
}

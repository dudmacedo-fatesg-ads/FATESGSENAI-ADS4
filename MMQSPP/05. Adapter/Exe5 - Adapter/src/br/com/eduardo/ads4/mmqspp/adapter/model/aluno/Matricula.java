package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

/**
 *
 * @author eduardo
 */
public class Matricula {
    private int ano;
    private int semestre;
    private int curso;
    private int numero;
    
    public Matricula(String matricula) {
        String dados[] = matricula.split("\\.");
        ano = Integer.parseInt(dados[0]);
        semestre = Integer.parseInt(dados[1]);
        curso = Integer.parseInt(dados[2]);
        numero = Integer.parseInt(dados[3]);
    }
    public Matricula(int ano, int semestre, int curso, int numero) {
        this.ano = ano;
        this.semestre = semestre;
        this.curso = curso;
        this.numero = numero;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the semestre
     */
    public int getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the curso
     */
    public int getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(int curso) {
        this.curso = curso;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    @Override
    public String toString() {
        return String.format("%d.%d.%03d.%04d", ano, semestre, curso, numero);
    }
}

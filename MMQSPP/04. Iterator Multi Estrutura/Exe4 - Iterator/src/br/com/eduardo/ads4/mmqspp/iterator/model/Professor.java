package br.com.eduardo.ads4.mmqspp.iterator.model;

/**
 *
 * @author eduardo
 */
public class Professor implements Comparable {

    private int matricula;
    private String nome;
    private String departamento;
    private String titulacao;
    private String regime;
    private int exp_docencia;
    private int exp_profissional;

    public Professor(int matricula, String nome, String departamento,
            String titulacao, String regime, int exp_docencia, int exp_profissional) {
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
        this.titulacao = titulacao;
        this.regime = regime;
        this.exp_docencia = exp_docencia;
        this.exp_profissional = exp_profissional;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public int getExp_docencia() {
        return exp_docencia;
    }

    public void setExp_docencia(int exp_docencia) {
        this.exp_docencia = exp_docencia;
    }

    public int getExp_profissional() {
        return exp_profissional;
    }

    public void setExp_profissional(int exp_profissional) {
        this.exp_profissional = exp_profissional;
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

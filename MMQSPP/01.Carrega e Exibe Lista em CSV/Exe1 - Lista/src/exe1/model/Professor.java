package exe1.model;

/**
 *
 * @author eduardo
 */
public class Professor {

    private int matricula;
    private String nome;
    private Departamento departamento;
    private Titulacao titulacao;
    private Regime regime;
    private int exp_docencia;
    private int exp_profissional;

    public Professor(int matricula, String nome, Departamento departamento,
            Titulacao titulacao, Regime regime, int exp_docencia, int exp_profissional) {
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
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
}

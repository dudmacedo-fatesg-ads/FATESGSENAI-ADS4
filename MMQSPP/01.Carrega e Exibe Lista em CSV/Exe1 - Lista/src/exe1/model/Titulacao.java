package exe1.model;

/**
 *
 * @author eduardo
 */
public enum Titulacao {
    ESPECIALISTA("Especialista"),
    MESTRE("Mestre(a)"),
    DOUTOR("Doutor(a)"),
    POSDOUTOR("Pos-Doutor(a)");
    
    private final String nome;
    
    private Titulacao(String nome) {
        this.nome = nome;
    }
    
    public static Titulacao getTitulacao(String titulacao) {
        for (Titulacao t : Titulacao.values()) {
            if (t.toString().equals(titulacao)) {
                return t;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}

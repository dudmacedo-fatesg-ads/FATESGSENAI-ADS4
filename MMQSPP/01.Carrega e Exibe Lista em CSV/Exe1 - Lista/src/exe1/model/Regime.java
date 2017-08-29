package exe1.model;

/**
 *
 * @author eduardo
 */
public enum Regime {
    TI("TI"),
    HORISTA("Horista");
    
    private final String nome;
    
    private Regime(String nome) {
        this.nome = nome;
    }
    
    public static Regime getRegime(String regime) {
        for (Regime r : Regime.values()) {
            if (r.toString().equals(regime)) {
                return r;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}

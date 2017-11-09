package entidade;

/**
 *
 * @author eduardo
 */
public class EContaCorrente {
    private Double saldoAtual;
    
    public EContaCorrente() {
        
    }
    public EContaCorrente(Double saldoInicial) throws IllegalArgumentException {
        if (saldoInicial != null)
        saldoAtual = saldoInicial;
        else
            throw new IllegalArgumentException("Não é permitido valor nulo para o saldo da conta!");
    }

    public Double getSaldoAtual() {
        if (saldoAtual == null) {
            return 0.0;
        }
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) throws IllegalArgumentException {
        if (saldoAtual != null)
        this.saldoAtual = saldoAtual;
        else
            throw new IllegalArgumentException("Não é permitido valor nulo para o saldo da conta!");
    }
}

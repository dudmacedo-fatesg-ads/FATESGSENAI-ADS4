package negocio;

import entidade.EContaCorrente;
import persistencia.PMovimentacao;

/**
 *
 * @author eduardo
 */
public class NMovimentacao {

    public NMovimentacao(PMovimentacao pmovimentacao) {
        this.pmovimentacao = pmovimentacao;
    }

    PMovimentacao pmovimentacao;

    public boolean registrar(EContaCorrente conta) {
        return pmovimentacao.registrar(conta);
    }
}

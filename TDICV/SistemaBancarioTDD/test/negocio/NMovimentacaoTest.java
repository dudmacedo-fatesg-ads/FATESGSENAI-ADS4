package negocio;

import entidade.EContaCorrente;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import persistencia.PMovimentacao;

/**
 *
 * @author eduardo
 */
public class NMovimentacaoTest {

    /**
     * Test of registrar method, of class NMovimentacao.
     */
    @Test
    public void testRegistrar() {
        System.out.println("Teste método registrar()");

        EContaCorrente conta = new EContaCorrente(0.0);

        PMovimentacao pmovimentacao = Mockito.mock(PMovimentacao.class);
        NMovimentacao instance = new NMovimentacao(pmovimentacao);

        Mockito.when(pmovimentacao.registrar(conta)).thenReturn(true);

        boolean expResult = true;
        boolean result = instance.registrar(conta);
        assertEquals(expResult, result);
    }

}

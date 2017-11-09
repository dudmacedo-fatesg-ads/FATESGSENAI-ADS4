package entidade;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eduardo
 */
public class EContaCorrenteTest {

    /**
     * Caso de Teste 04
     */
    @Test
    public void testCase04() {
        EContaCorrente instance = new EContaCorrente(new Double(15));
        Double expResult = 15.0;
        assertEquals(expResult, instance.getSaldoAtual());
    }

    /**
     * Caso de Teste 05
     */
    @Test
    public void testCase05() {
        EContaCorrente instance = new EContaCorrente(new Double(10));
        instance.setSaldoAtual(instance.getSaldoAtual() + 30);
        Double expResult = 40.0;
        assertEquals(expResult, instance.getSaldoAtual());
    }

    /**
     * Caso de Teste 06
     */
    @Test
    public void testCase06() {
        EContaCorrente instance = new EContaCorrente(new Double(0));
        instance.setSaldoAtual(instance.getSaldoAtual() + 10);
        Double expResult = 10.0;
        assertEquals(expResult, instance.getSaldoAtual());
    }
}

package br.com.eduardo.ads4.mmqspp.observer;

/**
 *
 * @author eduardo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Novo Pregoeiro
        Pregoeiro pregoeiro = new Pregoeiro(2500);
        
        // Expectadores
        Expectador exp1 = new Expectador("João");
        Expectador exp2 = new Expectador("José");
        Expectador exp3 = new Expectador("Maria");
        Expectador exp4 = new Expectador("Tereza");
        
        pregoeiro.adicionaObserver(exp1);
        pregoeiro.adicionaObserver(exp2);
        pregoeiro.adicionaObserver(exp3);
        pregoeiro.adicionaObserver(exp4);
        
        // Licitantes
        Licitante lic1 = new Licitante("Fazenda Arapoã", pregoeiro);
        Licitante lic2 = new Licitante("FreeBoi.org", pregoeiro);
        
        pregoeiro.adicionaObserver(lic1);
        pregoeiro.adicionaObserver(lic2);
        
        // Operações
        lic1.registraLance(2400);
        lic2.registraLance(2600);
        lic1.registraLance(2650);
        
        pregoeiro.removeObserver(exp3);
        
        lic2.registraLance(2700);
    }

}

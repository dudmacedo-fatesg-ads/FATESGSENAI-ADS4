package br.com.eduardo.banco;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//        Conta c1 = new Conta(20498);
            Conta c1 = new Conta(376, 1000);
//        Conta c1 = new Conta(40821, 1000);

            ExecutorService executor = Executors.newFixedThreadPool(2);

            executor.execute(new Terminal(1, c1, 'S'));
            executor.execute(new Terminal(2, c1, 'D'));

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}


package br.com.eduardo.corridadesapos;

/**
 *
 * @author eduardo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread[] sapos = new Thread[12];
        for (int i = 0; i < sapos.length; i++) {
            sapos[i] = new Thread(new Sapo(i + 1));
        }
        Podio.percurso = 2000000000;
        
        for (Thread t : sapos) {
            t.start();
        }
    }
    
}

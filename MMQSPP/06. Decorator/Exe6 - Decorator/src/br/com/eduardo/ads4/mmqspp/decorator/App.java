package br.com.eduardo.ads4.mmqspp.decorator;

import br.com.eduardo.ads4.mmqspp.decorator.model.*;

/**
 *
 * @author eduardo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Computador note = 
                new Tela("Tela LED Full HD retroiluminada (1920X1080) 15.6\"", 800,
                new PlacaVideo("NVIDIA® GeForce® GTX 960M 4 GB DDR5", 1200,
                new DiscoRigido("SSHD 1 TB + 8 GB", 350,
                new MemoriaRAM("16GB DDR4", 600,
                new Processador("Intel Core i7 6500U", 1000, 
                        new Notebook("Dell Inspiron 15 Gaming", 800))))));
        
        System.out.println(note.getDescricao());
        System.out.println(String.format("Valor Total: R$ %.2f", note.getValor()));
        
        Computador desk =
                new Tela("Monitor LG 22\" IPS", 700,
                new PlacaVideo("NVIDIA® GeForce® GTX 1080 8GB GDDR5", 1999.99,
                new SSD("SSD Kingston 240GB", 400,
                new DiscoRigido("HD Seagate Barracuda 1 TB 7200 RPM", 400,
                new MemoriaRAM("32 GB DDR4 2400Mhz", 1500,
                new Processador("Intel Core i7 7700K", 1500,
                        new Desktop("MaxTech X8 GE", 1500)))))));
        
        System.out.println(desk.getDescricao());
        System.out.println(String.format("Valor Total: R$ %.2f", desk.getValor()));
    }
    
}

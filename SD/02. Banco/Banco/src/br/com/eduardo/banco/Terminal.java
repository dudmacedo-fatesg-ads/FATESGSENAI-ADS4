package br.com.eduardo.banco;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class Terminal extends Thread {

    private final int id;
    private final Conta conta;
    private char op; // S = sacar primeiro, T = sempre sacar, D = depositar primeiro, E = sempre depositar, I = aleatório 

    public Terminal(int id, Conta conta, char op) {
        this.id = id;
        this.conta = conta;
        this.op = op;
    }

    public void depositar(double valor) {
        synchronized (conta) {
            conta.depositar(valor);
            System.out.println(String.format("T%d: Depositou: R$ %.2f - Saldo: R$ %.2f", id, valor, conta.getSaldo()));
            conta.notifyAll();
        }
    }

    public void sacar(double valor) {
        synchronized (conta) {
            while (true) {
                if (conta.getSaldo() >= valor) {
                    conta.sacar(valor);
                    System.out.println(
                            String.format("T%d: Sacou R$ %.2f - Saldo: R$ %.2f", id, valor, conta.getSaldo()));
                    break;
                } else {
                    System.out.println(
                            String.format("T%d: Aguarda saldo para saque de R$ %.2f - Saldo: R$ %.2f", id, valor, conta.getSaldo()));
                    try {
                        conta.wait();
                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        Random rand_valor = new Random();
        for (int i = 0; i < 20; i++) { // cada terminal efetuará 20 operações
            double valor = (double) rand_valor.nextInt(1000);

            char it_op = op;
            if (it_op == 'I') {
                it_op = (new Random().nextInt(2) == 1) ? 'D' : 'S';
            }

            switch (it_op) {
                case 'S':
                    op = 'I';
                case 'T':
                    sacar(valor);
                    break;

                case 'D':
                    op = 'I';
                case 'E':
                    depositar(valor);
                    break;

            }
        }
    }
}

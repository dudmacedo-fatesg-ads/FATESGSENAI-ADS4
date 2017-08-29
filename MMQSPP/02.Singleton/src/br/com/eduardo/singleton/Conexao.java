package br.com.eduardo.singleton;

/**
 *
 * @author eduardo
 */
public class Conexao {

    private static Conexao cnx = null;

    private Conexao() {
    }

    public static Conexao getConexao() {
        if (cnx == null) {
            cnx = new Conexao();
        }
        return cnx;
    }
}

package br.com.eduardo.floricultura.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author eduardo
 */
public class Formatador {

    public static String formataData(Date data) {
        return new SimpleDateFormat("dd/MM/yyyy").format(data);
    }
    
    public static String formataIdfPJ(long idf) {
        String cnpj = String.format("%014d", idf);
        cnpj = String.format(
                "%s.%s.%s-%s/%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12));
        return cnpj;
    }

    public static String formataIdfPF(long idf) {
        String cpf = String.format("%011d", idf);
        cpf = String.format(
                "%s.%s.%s-%s",
                cpf.substring(0, 3),
                cpf.substring(3, 6),
                cpf.substring(6, 9),
                cpf.substring(9));
        return cpf;
    }
}

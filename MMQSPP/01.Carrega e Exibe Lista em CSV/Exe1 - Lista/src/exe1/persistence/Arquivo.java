package exe1.persistence;

import exe1.model.Professor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author dudma
 */
public class Arquivo {

    public static ArrayList<Professor> getProfessores(File arq) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(arq);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<Professor> lista = new ArrayList<>();

        while (br.ready()) {
            String[] linha = br.readLine().split(";");

            lista.add(
                    new Professor(
                            Integer.parseInt(linha[0].trim()),
                            linha[1],
                            linha[2],
                            linha[3],
                            linha[4],
                            Integer.parseInt(linha[5].trim()),
                            Integer.parseInt(linha[6].trim())
                    ));
        }

//        lista.sort(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return ((Professor) o1).getNome()
//                        .compareToIgnoreCase(((Professor) o2).getNome());
//            }
//        });

        br.close();
        fr.close();

        return lista;
    }
}

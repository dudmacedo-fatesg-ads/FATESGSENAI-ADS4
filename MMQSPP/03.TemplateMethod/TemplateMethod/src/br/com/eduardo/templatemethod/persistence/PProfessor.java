package br.com.eduardo.templatemethod.persistence;

import br.com.eduardo.templatemethod.model.Professor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author eduardo
 */
public abstract class PProfessor {

    private final File arquivo;

    public PProfessor(File arquivo) {
        this.arquivo = arquivo;
    }

    // Retorna:
    // 0 = objetos iguais
    // < 0 = a1 menor que a2
    // > 0 = a1 maior que a2
    public abstract int comparar(Professor p1, Professor p2);

    public Iterator<Professor> listarAlunos() throws FileNotFoundException, IOException {
        ArrayList<Professor> professores = new ArrayList<>();

        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        if (br.ready()) {
            br.readLine();
        }

        while (br.ready()) {
            String[] linha = br.readLine().split(";");

            professores.add(
                    new Professor(
                            Integer.parseInt(linha[0]),
                            linha[1],
                            linha[2],
                            linha[3],
                            linha[4],
                            Integer.parseInt(linha[5]),
                            Integer.parseInt(linha[6])
                    ));
        }

        br.close();
        fr.close();

        // Ordenando
        for (int i = 0; i < professores.size(); i++) {
            for (int j = i; j < professores.size(); j++) {
                if (comparar(professores.get(i), professores.get(j)) > 0) {
                    Professor aux = professores.get(j);
                    professores.set(j, professores.get(i));
                    professores.set(i, aux);
                }
            }
        }

        return professores.iterator();
    }
}

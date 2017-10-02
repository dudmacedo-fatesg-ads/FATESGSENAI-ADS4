package br.com.eduardo.ads4.mmqspp.adapter.model.aluno;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class AlunoAdapter implements IAluno {

    private AlunoLegado aluno;

    public AlunoAdapter(AlunoLegado aluno) {
        this.aluno = aluno;
    }

    @Override
    public Matricula getMatricula() {
        return new Matricula(aluno.getMatricula());
    }

    @Override
    public void setMatricula(Matricula matricula) {
        aluno.setMatricula(matricula.toString());
    }

    @Override
    public String getCurso() {
//        028 = Ciência da Computação
//        033 = Engenharia da Computação
//        048 = Engenharia de Software
        switch (new Matricula(aluno.getMatricula()).getCurso()) {
            case 28:
                return "Ciência da Computação";
            case 33:
                return "Engenharia da Computação";
            case 48:
                return "Engenharia de Software";
            default:
                return "Não definido";
        }
    }

    @Override
    public String getNome() {
        return aluno.getNome();
    }

    @Override
    public void setNome(String nome) {
        aluno.setNome(nome);
    }

    @Override
    public ArrayList<Disciplina> getDisciplinas() {
        ArrayList<Disciplina> ret = new ArrayList<>();
        for (String d : aluno.getDisciplinas()) {
            ret.add(new Disciplina(d));
        }
        return ret;
    }

    @Override
    public void setDisciplinas(List<Disciplina> disciplinas) {
        String[] disc = new String[disciplinas.size()];
        for (int i = 0; i < disc.length; i++) {
            disc[i] = disciplinas.get(i).toString();
        }
        aluno.setDisciplinas(disc);
    }

    public Aluno getAluno() {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        for (String s : aluno.getDisciplinas()) {
            disciplinas.add(new Disciplina(s));
        }
        return new Aluno(
                new Matricula(aluno.getMatricula()),
                aluno.getNome(),
                disciplinas);
    }
}
